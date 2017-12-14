package session;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class CreateSessionWithFluentStyleExample {
    public static final String zkAddress = "localhost:2181";
    public static final String PATH = "/curator-lab";

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = null;
        try {
            client = CuratorFrameworkFactory.builder()
                    .connectString(zkAddress)
                    .sessionTimeoutMs(5000)
                    .retryPolicy(retryPolicy)
                    .build();
            client.start();

            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(PATH, "data goes to".getBytes());

            String data = new String(client.getData().forPath(PATH));
            System.out.println(String.format("Data on Zookeeper: %s", data));

            client.delete()
                    .forPath(PATH);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(client != null) {
                client.close();
            }
        }
    }
}
