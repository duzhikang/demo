package com.dzk.threadpool.concurrency.example.atomic.singleton;/**
 * Created by dzk on 2020/2/6.
 */

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 线程不安全。
 */
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance() {
        // 多线程可能出现问题
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
