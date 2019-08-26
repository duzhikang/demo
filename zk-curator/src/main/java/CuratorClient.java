import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

/**
 * <p>ClassName: CuratorClient</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/12
 * @since JDK 1.8
 */
public class CuratorClient {

    public CuratorFramework client = null;
    public static final String zkServerPath = "192.168.234.188:2181";

    /**
     * 实例化zk客户端
     */
    public CuratorClient() {
        /**
         * 同步创建zk示例，原生api是异步的
         *
         * curator链接zookeeper的策略:ExponentialBackoffRetry
         * baseSleepTimeMs：初始sleep的时间
         * maxRetries：最大重试次数
         * maxSleepMs：最大重试时间
         */
//		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);

        /**
         * curator链接zookeeper的策略:RetryNTimes
         * n：重试的次数
         * sleepMsBetweenRetries：每次重试间隔的时间
         */
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);

        /**
         * curator链接zookeeper的策略:RetryOneTime
         * sleepMsBetweenRetry:每次重试间隔的时间
         */
//		RetryPolicy retryPolicy2 = new RetryOneTime(3000);

        /**
         * 永远重试，不推荐使用
         */
//		RetryPolicy retryPolicy3 = new RetryForever(retryIntervalMs)

        /**
         * curator链接zookeeper的策略:RetryUntilElapsed
         * maxElapsedTimeMs:最大重试时间
         * sleepMsBetweenRetries:每次重试间隔
         * 重试时间超过maxElapsedTimeMs后，就不再重试
         */
//		RetryPolicy retryPolicy4 = new RetryUntilElapsed(2000, 3000);

        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000).retryPolicy(retryPolicy)
                .namespace("workspace").build();
        client.start();
    }

    /**
     *
     * @Description: 关闭zk客户端连接
     */
    public void closeZKClient() {
        if (client != null) {
            this.client.close();
        }
    }

    public static void main(String[] args) throws Exception {
        CuratorClient curator = new CuratorClient();
        boolean started = curator.client.isStarted();
        System.out.println("当前状态：" + started);

        String nodepath = "/super/imooc";
        // 创建节点
        /*String nodepath = "/super/imooc";
        byte[] bytes = "superme".getBytes();
        curator.client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(nodepath, bytes);*/
        /*byte[] bytes = "batman".getBytes();
        curator.client.setData().withVersion(0).forPath(nodepath, bytes);
    */

        /*curator.client.delete()
                .guaranteed() // // 如果删除失败，那么在后端还是继续会删除，直到成功
                .deletingChildrenIfNeeded() // 如果有子节点，就删除
                .withVersion(4)
                .forPath(nodepath);*/
        Stat stat = new Stat();
        byte[] bytes = curator.client.getData().storingStatIn(stat).forPath(nodepath);
        System.out.println(stat);
    }

}
