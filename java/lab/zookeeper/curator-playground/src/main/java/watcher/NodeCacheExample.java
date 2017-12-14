package watcher;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class NodeCacheExample {
    public static final String PATH = "/zk-book/nodecache";
    public static final String zkAddress = "localhost:2181";
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkAddress)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(5000)
                .build();
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(PATH, "init".getBytes());

        // create NodeCache
        final NodeCache nodeCache = new NodeCache(client, PATH, false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println(String.format("Node data updated, new data: %s", new String(nodeCache.getCurrentData().getData())));
            }
        });
        client.setData()
                .forPath(PATH, "data".getBytes());
        Thread.sleep(1000);
        // delete zNode => 不會驅動NodeCache listener
        client.delete()
                .deletingChildrenIfNeeded()
                .forPath(PATH);
        Thread.sleep(5000);

        // 這樣/zk-book會是persist zNode
    }
}
