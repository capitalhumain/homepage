package lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class WithDistributedLock {
    private static final String LockPath = "/zk-book-lock-path";
    private static final String zkConnectString = "localhost:2181";

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .sessionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();

        final InterProcessMutex interProcessMutex = new InterProcessMutex(client, LockPath);
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        for(int i=0; i<10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(String.format("%s 準備執行", Thread.currentThread().getName()));
                        countDownLatch.await();
                        interProcessMutex.acquire();
                    } catch(Exception e) {}
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println(String.format("%s 產生的訂單編號是 %s", Thread.currentThread().getName(), orderNo));
                    try {
                        interProcessMutex.release();
                    } catch(Exception e) {}
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
