package leader;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class LeaderSelectExample {
    private static final String LeaderSelectPath = "/zk-book-leader-select-path";
    private static final String zkConnectString = "localhost:2181";
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(3000)
                .build();
        client.start();

        LeaderSelector leaderSelector = new LeaderSelector(client, LeaderSelectPath, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("成為Leader角色");
                // 做leader該做的事情
                Thread.sleep(3000);
                System.out.println("完成Leader工作，釋放Leader權利");
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
