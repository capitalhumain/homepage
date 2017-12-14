package async;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class AsyncWatchExample {
    private static final String Path = "/zk-book/boo";
    private static final String zkConnectString = "localhost:2181";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        AsyncCuratorFramework asyncClient = AsyncCuratorFramework.wrap(client);

        Stat stat = client.checkExists()
                .forPath(Path);

        if(null == stat) {
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(Path, "init".getBytes());
        }
        Thread.sleep(2000);

        asyncClient.watched()
                .getData()
                .forPath(Path)
                .event()
                .thenAccept(watchedEvent -> System.out.println(watchedEvent.toString()));

        Thread.sleep(3000);
    }
}
