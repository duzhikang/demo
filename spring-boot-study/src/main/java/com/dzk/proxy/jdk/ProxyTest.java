package com.dzk.proxy.jdk;/**
 * Created by dzk on 2020/3/30.
 */

import org.junit.Test;

/**
 * @ClassName ProxyTest
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/30
 **/
public class ProxyTest {

    // JdkDynamicAopProxy
    @Test
    public void test() {
        UserService userService = new UserServiceImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
        UserService proxy = (UserService) invocationHandler.getProxy();
        proxy.add();
    }
}
