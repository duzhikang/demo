import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * <p>ClassName: ZkConnection</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/8
 * @since JDK 1.8
 */
public class ZkConnection {

    final static CountDownLatch connectedSignal = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException, IOException {
        ZooKeeper zoo = new ZooKeeper("192.168.234.255:2181",5000,new Watcher() {

            @Override
            public void process(WatchedEvent we) {

                if (we.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        Thread.sleep(5000);
        System.out.println(zoo.getState());
    }

}
