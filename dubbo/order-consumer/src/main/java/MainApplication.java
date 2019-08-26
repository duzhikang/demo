import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.OrderService;

import java.io.IOException;

/**
 * <p>ClassName: MainApplication</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/15
 * @since JDK 1.8
 */
public class MainApplication {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");

        OrderService orderService = (OrderService) applicationContext.getBean("orderService");

        orderService.initOrder("1");
        System.out.println("调用完成....");
        System.in.read();
    }
}
