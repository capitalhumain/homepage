package session;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncExample {
    public static final String PATH = "/zk-book";
    public static final String zkAddress = "localhost:2181";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkAddress)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        client.start();

        System.out.println(String.format("Main Thread %s", Thread.currentThread().getName()));
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println(String.format(">>> event[code: %d, type: %s]", curatorEvent.getResultCode(), curatorEvent.getType()));
                        System.out.println(String.format(">>> Thread of processResult %s", Thread.currentThread().getName()));
                        countDownLatch.countDown();
                    }
                }, executorService)
                .forPath(PATH, "init".getBytes());
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println(String.format("event[code: %d, type: %s]", curatorEvent.getResultCode(), curatorEvent.getType()));
                        System.out.println(String.format("Thread of processResult %s", Thread.currentThread().getName()));
                        countDownLatch.countDown();
                    }
                })
                .forPath(PATH, "init".getBytes());
        countDownLatch.await();
        executorService.shutdown();
    }
}
