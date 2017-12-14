package async;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Op;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncExample1 {
    private static final String zkConnectString = "localhost:2181";
    private static final String Path = "/zk-book";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        // 要start()
        client.start();
        AsyncCuratorFramework asyncClient = AsyncCuratorFramework.wrap(client);
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        asyncClient.checkExists()
                .forPath(Path)
                .thenAccept(stat -> {
                    if(null == stat) {
                        System.out.println("Does not exist");
                    } else {
                        System.out.println(stat);
                    }
                    countDownLatch.countDown();
                });
        asyncClient.getChildren()
                .forPath("/")
                .thenAccept(listPath -> {
                    System.out.println(listPath);
                    countDownLatch.countDown();
                });
        System.out.println("Now waiting");
        countDownLatch.await(10, TimeUnit.SECONDS);
        if(countDownLatch.getCount() == 0) {
            System.out.println("Done.");
        } else {
            System.out.println("Failed.");
        }
    }
}
