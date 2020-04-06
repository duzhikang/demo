package com.dzk.threadpool.concurrency.example.atomic.singleton;/**
 * Created by dzk on 2020/2/6.
 */

/**
 * @ClassName SingletonExample2
 * @Description /**
 * 饿汉模式
 * 单例实例在类装载时进行创建，性能较差，线程安全。
 * @Author dzk
 * @Version v1
 * @Date 2020/2/6
 **/
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
