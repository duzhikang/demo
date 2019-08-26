package provider;

import api.GreetingService;

/**
 * <p>ClassName: GreetingsServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/15
 * @since JDK 1.8
 */
public class GreetingsServiceImpl implements GreetingService {
    public String sayHello(String name) {
        return "hi, " + name;
    }
}
