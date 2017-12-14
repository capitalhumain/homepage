package watcher;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class PathChildrenCacheExample {
    public static final String zkConnectionString = "localhost:2181";
    public static final String BasePath = "/zk-book";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectionString)
                .sessionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();

        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, BasePath, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch(event.getType()) {
                    case CHILD_ADDED:
                        System.out.println(String.format("CHILD_ADD: %s", event.getData().getPath()));
                        break;
                    case CHILD_UPDATED:
                        System.out.println(String.format("CHILD_UPDATED: %s", event.getData().getPath()));
                        break;
                    case CHILD_REMOVED:
                        System.out.println(String.format("CHILD_REMOVED: %s", event.getData().getPath()));
                        break;
                    default:
                        System.out.println("Unknown event type: " + event.getType());
                        break;
                }
            }
        });
        if(null == client.checkExists().forPath(BasePath)) {
            System.out.println(String.format("%s does not exists", BasePath));
            // KeeperException$NodeExistsException: KeeperErrorCode = NodeExists for /zk-book
//            client.create()
//                    .withMode(CreateMode.PERSISTENT)
//                    .forPath(BasePath);
        }
        Thread.sleep(1000);
        client.create()
                .withMode(CreateMode.PERSISTENT)
                .forPath(BasePath + "/e1");
        Thread.sleep(1000);
        if(null == client.checkExists().forPath(BasePath)) {
            System.out.println(String.format("%s does not exists", BasePath));
        } else {
            System.out.println(String.format("%s exists", BasePath));
        }
        Thread.sleep(1000);
        client.setData()
                .forPath(BasePath + "/e1", "init".getBytes());
        Thread.sleep(1000);
        client.delete()
                .forPath(BasePath + "/e1");
        Thread.sleep(1000);
        client.delete()
                .forPath(BasePath);
        Thread.sleep(3000);
    }
}
