package com.dzk.springboot;/**
 * Created by dzk on 2020/2/21.
 */

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName SpringEventDemo
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/21
 **/
public class SpringEventDemo {

    public static void main(String[] args) {
        // 创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册应用事件监听器
        context.addApplicationListener(event -> {
            System.out.println("监听到事件: " + event);
        });

        // 启动上下文
        context.refresh();
        // 发送事件
        context.publishEvent("HelloWorld");
        context.publishEvent("2018");
        context.publishEvent(new ApplicationEvent("小马哥") {

        });

        // 关闭上下文
        context.close();
    }
}
