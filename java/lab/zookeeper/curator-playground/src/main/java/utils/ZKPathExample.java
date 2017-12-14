package utils;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

public class ZKPathExample {
    private static final String zkConnectString = "localhost:2181";
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(5000)
                .build();
        client.start();
        client.usingNamespace("zk-book");

        ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();
        // exception path must starts with "/"
        // System.out.println(ZKPaths.fixForNamespace("/zk-book", "sub"));
        // /zk-book/sub
        System.out.println(ZKPaths.fixForNamespace("/zk-book", "/sub"));
        // /zk-book/sub
        System.out.println(ZKPaths.fixForNamespace("zk-book", "/sub"));
        // /zk-book/sub
        System.out.println(ZKPaths.makePath("/zk-book", "/sub"));
        // /zk-book/sub
        System.out.println(ZKPaths.makePath("zk-book", "/sub"));
        // sub
        System.out.println(ZKPaths.getNodeFromPath("/zk-book/sub"));

        System.out.println(ZKPaths.getSortedChildren(zooKeeper, "/"));
    }
}
