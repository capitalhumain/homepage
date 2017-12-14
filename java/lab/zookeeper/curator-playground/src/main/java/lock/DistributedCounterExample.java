package lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.CountDownLatch;

public class DistributedCounterExample {
    private static final String DistributedAtomicIntPath = "/zk-book-distatomicint";
    private static final String zkConnectString = "localhost:2181";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .sessionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        DistributedAtomicInteger distributedAtomicInteger = new DistributedAtomicInteger(client, DistributedAtomicIntPath, new RetryNTimes(3, 1000));
        AtomicValue<Integer> rc = distributedAtomicInteger.add(8);
        System.out.println(String.format("Result: %b", rc.succeeded()));

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i=0; i<10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        AtomicValue<Integer> value = distributedAtomicInteger.get();
                        System.out.println(String.format("Get: %d, %d", value.preValue(), value.postValue()));
                    } catch(Exception e) {}
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
