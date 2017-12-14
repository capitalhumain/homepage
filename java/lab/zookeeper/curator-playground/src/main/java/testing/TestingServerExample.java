package testing;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.io.File;

public class TestingServerExample {
    private static final String Path = "/zookeeper";
    public static void main(String[] args) throws Exception {
        TestingServer testingServer = new TestingServer(2181, new File("/Users/tzuyichao/data/zk-book-data"));
        //TestingServer testingServer = new TestingServer();

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(testingServer.getConnectString())
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .sessionTimeoutMs(5000)
                .build();
        client.start();
        System.out.println(client.getChildren().forPath(Path));
        testingServer.close();
    }
}
