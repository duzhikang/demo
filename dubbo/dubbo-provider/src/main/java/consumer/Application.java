package consumer;

import api.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * <p>ClassName: Application</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/15
 * @since JDK 1.8
 */
public class Application {

    private static String zookeeperHost = System.getProperty("zookeeper.address", "192.168.234.188");

    public static void main(String[] args) {
        ReferenceConfig<GreetingService> reference = new ReferenceConfig<GreetingService>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        reference.setInterface(GreetingService.class);
        GreetingService service = reference.get();
        String message = service.sayHello("dubbo");
        System.out.println(message);
    }
}
