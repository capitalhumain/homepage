package session;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class ExistsExample {
    public static final String zkConnectionString = "localhost:2181";
    public static final String BasePath = "/zk-book";
    public static final String BasePathNotExist = "/zk-book-not-exist";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectionString)
                .sessionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        Stat stat = client.checkExists()
                .forPath(BasePath);
        System.out.println(stat);
        // stat == null => path does not exist
        stat = client.checkExists()
                .forPath(BasePathNotExist);
        System.out.println(stat);
    }
}
