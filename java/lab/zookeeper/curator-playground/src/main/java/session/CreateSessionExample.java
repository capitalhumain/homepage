package session;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * from 倪超的範例
 */
public class CreateSessionExample {
    public static final String zkAddress = "localhost:2181";
    public static final String PATH = "/curator-lab";

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, 5000, 3000, retryPolicy);
        client.start();

        try {
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(PATH, "init-data".getBytes());
            byte[] data = client.getData()
                    .forPath(PATH);
            System.out.println(String.format("Data: %s", new String(data)));

            client.delete()
                    .forPath(PATH);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
