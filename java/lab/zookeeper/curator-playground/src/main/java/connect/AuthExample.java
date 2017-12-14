package connect;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 還是修改自倪超的範例
 */
public class AuthExample {
    public static final String AUTH_SCHEME_DIGEST = "digest";
    public static final String PATH = "/zk-book-auth-test";

    public static void main(String[] args) {
        ZooKeeperConnector zkConnector = new ZooKeeperConnector("localhost:2181");
        ZooKeeper zkSession1 = null, zkSession2 = null, zkSession3 = null;
        try {
            zkSession1 = zkConnector.createSession();
            zkSession1.addAuthInfo(AUTH_SCHEME_DIGEST, "foo:foopwd".getBytes());
            zkSession1.create(PATH, "initial data".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

            try {
                // org.apache.zookeeper.KeeperException$NoAuthException: KeeperErrorCode = NoAuth for /zk-book-auth-test
                zkSession2 = zkConnector.createSession();
                zkSession2.getData(PATH, false, null);
            } catch(KeeperException e) {
                e.printStackTrace();
            }

            try {
                // org.apache.zookeeper.KeeperException$NoAuthException: KeeperErrorCode = NoAuth for /zk-book-auth-test
                zkSession2 = zkConnector.createSession();
                zkSession2.setData(PATH, "failed".getBytes(), -1);
            } catch(KeeperException e) {
                e.printStackTrace();
            }

            zkSession3 = zkConnector.createSession();
            zkSession3.addAuthInfo(AUTH_SCHEME_DIGEST, "foo:foopwd".getBytes());
            String data = new String(zkSession3.getData(PATH, false, null));
            System.out.println(String.format("Data: %s", data));
        } catch(IOException | KeeperException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(zkSession1 != null) {
                try {
                    zkSession1.close();
                } catch(InterruptedException e) {}
            }
            if(zkSession2 != null) {
                try {
                    zkSession2.close();
                } catch(InterruptedException e) {}
            }
            if(zkSession3 != null) {
                try {
                    zkSession3.close();
                } catch(InterruptedException e) {}
            }
        }
    }
}
