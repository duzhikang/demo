import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * <p>ClassName: ZooKeeperConnection</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/9
 * @since JDK 1.8
 */
public class ZooKeeperConnection {

    final static Logger log = LoggerFactory.getLogger(ZooKeeperConnection.class);

    // declare zookeeper instance to Access ZooKeeper ensemble
    private ZooKeeper zoo;
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    // Method to connect zookeeper ensemble.
    public ZooKeeper connect(String host) throws IOException,InterruptedException {

        zoo = new ZooKeeper(host,5000,new Watcher() {

            public void process(WatchedEvent we) {
                if (we.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    public ZooKeeper reconnect(String host) throws IOException, InterruptedException {
        zoo = new ZooKeeper(host, 5000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("we" + watchedEvent);

                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });
        log.warn("zkSession：{}", zoo.getState());
        long sessionId = zoo.getSessionId();
        byte[] sessionPasswd = zoo.getSessionPasswd();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("zkSession：{}", zoo.getState());
        // 开始会话重连
        log.warn("开始会话重连...");
        zoo = new ZooKeeper(host,5000, new Watcher() {
                    public void process(WatchedEvent watchedEvent) {
                        System.out.println("we" + watchedEvent);

                        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                            connectedSignal.countDown();
                        }
                    }
                },
                sessionId,
                sessionPasswd);
        log.warn("重新连接状态zkSession：{}", zoo.getState());
        Thread.sleep(1000);
        log.warn("重新连接状态zkSession：{}", zoo.getState());
        return zoo;
    }

    // Method to disconnect from zookeeper server
    public void close() throws InterruptedException {
        if (zoo != null) {
            zoo.close();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeperConnection connection = new ZooKeeperConnection();
        System.out.println("******");
        System.out.println(connection.reconnect("192.168.234.188:2181"));
    }
}
