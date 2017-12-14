package connect;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Modify from 倪超的書
 */
public class ZookeeperConstructorUsageSimple {
    private static final String Path_Persistent_Test = "/zk-persist-test";
    private static final String Path_Persistent_Test2 = "/zk-persist-test/test.json";

    private static final String EXAMPLE_JSON_DATA = "{\"enable\": true, \"genre\": [\"Jazz\", \"Rock\"]}";

    public static void main(String[] args) {
        ZooKeeperConnector zkConnector = new ZooKeeperConnector("localhost:2181");
        ZooKeeper zkSession = null;
        try {
            zkSession = zkConnector.createSession();
            if(zkSession != null) {
                String znode_path1 = zkSession.create("/zk-ephemeral-test", "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println(String.format("Success. create EPHEMERAL zNode: %s", znode_path1));

                String znode_path2 = zkSession.create(Path_Persistent_Test, "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                System.out.println(String.format("Success. create PERSISTENT zNode: %s", znode_path2));

                String znode_path3 = zkSession.create(Path_Persistent_Test2, EXAMPLE_JSON_DATA.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                System.out.println(String.format("Success. create PERSISTENT zNode of %s: %s", znode_path2, znode_path3));

                Stat stat = new Stat();
                String getDataFromPersistentTest2 = new String(zkSession.getData(Path_Persistent_Test2, null, stat));
                System.out.println(getDataFromPersistentTest2);
                System.out.println(EXAMPLE_JSON_DATA.equals(getDataFromPersistentTest2));
                System.out.println(stat.toString());

                try {
                    Thread.sleep(30000);
                } catch(InterruptedException e) {}

                // 根據JavaDoc: if the given version is -1, it matches any node's versions
                // child zNode沒刪除，parent zNode就無法刪除
                zkSession.delete(Path_Persistent_Test2, -1);

                zkSession.delete(Path_Persistent_Test, -1);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(KeeperException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(zkSession != null) {
                try {
                    zkSession.close();
                } catch(InterruptedException e) {}
            }
        }
    }
}
