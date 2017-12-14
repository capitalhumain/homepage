package testing;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

public class TestingClusterExample {
    public static void main(String[] args) throws Exception {
        TestingCluster testingCluster = new TestingCluster(3);
        testingCluster.start();
        Thread.sleep(2000);

        System.out.println(String.format("TestingCuster Connect String: %s", testingCluster.getConnectString()));
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(testingCluster.getConnectString())
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        System.out.println(client.getChildren().forPath("/"));

        TestingZooKeeperServer leader = null;
        for(TestingZooKeeperServer zk: testingCluster.getServers()) {
            System.out.println(String.format("%s-%s-%s", zk.getInstanceSpec().getServerId(),
                    zk.getQuorumPeer().getServerState(),
                    zk.getInstanceSpec().getDataDirectory()));
            if(zk.getQuorumPeer().getServerState().equals("leading")) {
                leader = zk;
            }
        }
        leader.kill();
        System.out.println("=== After kill leader");
        for(TestingZooKeeperServer zk: testingCluster.getServers()) {
            System.out.println(String.format("%s-%s-%s", zk.getInstanceSpec().getServerId(),
                    zk.getQuorumPeer().getServerState(),
                    zk.getInstanceSpec().getDataDirectory()));
        }
        System.out.println(String.format("TestingCuster Connect String: %s", testingCluster.getConnectString()));

        Thread.sleep(2000);

        System.out.println("=== Leader Election");
        for(TestingZooKeeperServer zk: testingCluster.getServers()) {
            System.out.println(String.format("%s-%s-%s", zk.getInstanceSpec().getServerId(),
                    zk.getQuorumPeer().getServerState(),
                    zk.getInstanceSpec().getDataDirectory()));
        }

        testingCluster.stop();
    }
}
