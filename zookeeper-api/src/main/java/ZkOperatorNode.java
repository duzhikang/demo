import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>ClassName: ZkOperatorNode</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/9
 * @since JDK 1.8
 */
public class ZkOperatorNode {
    final static Logger log = LoggerFactory.getLogger(ZkOperatorNode.class);

    private static final String HOST = "192.168.234.188:2181";

    public static void createNode(String path, byte[] data) throws IOException, InterruptedException, KeeperException {
        ZooKeeperConnection conn = new ZooKeeperConnection();
        ZooKeeper zoo = conn.connect(HOST);
        /**
         * 同步或者异步创建节点，都不支持子节点的递归创建，异步有一个callback函数
         * 参数：
         * path：创建的路径
         * data：存储的数据的byte[]
         * acl：控制权限策略
         * 			Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
         * 			CREATOR_ALL_ACL --> auth:user:password:cdrwa
         * createMode：节点类型, 是一个枚举
         * 			PERSISTENT：持久节点
         * 			PERSISTENT_SEQUENTIAL：持久顺序节点
         * 			EPHEMERAL：临时节点
         * 			EPHEMERAL_SEQUENTIAL：临时顺序节点
         */
        /*zoo.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);*/
        // 异步
        String ctx = "{'create':'success'}";
        zoo.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
            public void processResult(int i, String s, Object o, String s1) {
                log.warn(String.valueOf(i));
                log.warn(s);
                log.warn(String.valueOf(0));
                log.warn(s1);
            }
        }, ctx);

        conn.close();
    }

    public static void update() throws IOException, InterruptedException, KeeperException {
        ZooKeeperConnection conn = new ZooKeeperConnection();
        ZooKeeper zoo = conn.connect(HOST);
        Stat status  = zoo.setData("/api-test-node", "222".getBytes(), 2);
        System.out.println(status.getVersion());
        conn.close();
    }

    public static void delete() throws IOException, InterruptedException, KeeperException {
        ZooKeeperConnection conn = new ZooKeeperConnection();
        ZooKeeper zoo = conn.connect(HOST);
        //zoo.delete("/api-test-node2", 0);
        String ctx = "{'delete':'success'}";
        zoo.delete("/test-delete-node2", 0, new AsyncCallback.VoidCallback() {

            public void processResult(int i, String s, Object o) {
                System.out.println(i);
                System.out.println(s);
                System.out.println(o);
            }
        }, ctx);
        conn.close();
    }


    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        //createNode("/api-test-node2", "123456".getBytes());
        //update();
        delete();
    }
}
