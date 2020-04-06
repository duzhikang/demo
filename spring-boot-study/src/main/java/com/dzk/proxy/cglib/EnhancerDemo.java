package com.dzk.proxy.cglib;/**
 * Created by dzk on 2020/3/30.
 */


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName EnhancerDemo
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/30
 **/
public class EnhancerDemo {

    // Cglib2AopProxy
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerDemo.class);
        enhancer.setCallback(new MethodInteceptorImpl());

        EnhancerDemo demo = (EnhancerDemo) enhancer.create();
        demo.test();
        System.out.println(demo);
    }


    public void test() {
        System.out.println("-----EnhancerDemo.test()----------");
    }

    private static class MethodInteceptorImpl implements MethodInterceptor {


        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before invoke " + method);

            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("after invoke " + method);
            return result;
        }
    }
}
