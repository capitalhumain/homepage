package znode;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

import java.util.List;

public class PersistentSequentialExample {
    private static final String zkConnectString = "localhost:2181";
    private static final String Path = "/zk-book/jobs/job-";

    public static void main(String[] args) throws Exception  {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(5000)
                .build();
        client.start();

        for(int i=0; i<5; i++) {
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(Path, "init".getBytes());
        }

        ZKPaths.PathAndNode pan = ZKPaths.getPathAndNode(Path);
        System.out.println(pan.getPath());
        System.out.println(pan.getNode());

        System.out.println(ZKPaths.getSortedChildren(client.getZookeeperClient().getZooKeeper(), pan.getPath()));

        List<String> children = client.getChildren()
                .forPath(pan.getPath());

        children.forEach(System.out::println);
    }
}
