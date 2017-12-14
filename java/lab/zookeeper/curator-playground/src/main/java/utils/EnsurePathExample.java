package utils;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class EnsurePathExample {
    private static final String zkConnectString = "localhost:2181";
    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        client.usingNamespace("zk-book");

        // EnsurePath Deprecated
        // Since 2.9.0 - Prefer CuratorFramework.create().creatingParentContainersIfNeeded() or CuratorFramework.exists().creatingParentContainersIfNeeded()
    }
}
