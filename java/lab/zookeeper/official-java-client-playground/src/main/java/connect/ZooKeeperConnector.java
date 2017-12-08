package connect;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZooKeeperConnector implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static final int zkConnectionTimeout = 5000;
    public static final int Timeout_Await = 5000;
    private String zkConnectStr;

    public ZooKeeperConnector(String zkConnectStr) {
        this.zkConnectStr = zkConnectStr;
    }

    public ZooKeeper createSession() throws IOException {
        ZooKeeper zookeeper = new ZooKeeper(zkConnectStr, zkConnectionTimeout, this);
        System.out.println(zookeeper);
        try {
            if(!connectedSemaphore.await(Timeout_Await, TimeUnit.MILLISECONDS)) {
                System.out.println("Wait Timeout");
                return null;
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ZooKeeper session established.");
        return zookeeper;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: " + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectedSemaphore.countDown();
        }
    }
}
