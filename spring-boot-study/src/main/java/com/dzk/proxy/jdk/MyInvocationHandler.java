package com.dzk.proxy.jdk;/**
 * Created by dzk on 2020/3/30.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName MyInvocationHandler
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/30
 **/
public class MyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("target method executor before");
        // 执行目标方法
        Object invoke = method.invoke(target, args);
        System.out.println("target method executor end");
        return invoke;
    }

    /**
     * 获取目标对象的代理对象
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);

    }
}
