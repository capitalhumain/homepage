package lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class DistributedBarrierExample {
    private static final String DistributedBarrierPath = "/zk-book-distributed-barrier";
    private static final String zkConnectString = "localhost:2181";
    public static void main(String[] args) {
        DistributedBarrier distributedBarrier;

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(5000)
                .build();
    }
}
