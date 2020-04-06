package com.dzk.threadpool.concurrency.example.atomic.singleton;/**
 * Created by dzk on 2020/2/6.
 */

/**
 * @ClassName SingletonExample4
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/6
 **/
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {

    }

    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // 单例对象 volatile + 双重检测机制 -> 禁止指令重排
    private volatile static SingletonExample4 instance = null;

    // 静态的工厂方法
    public static SingletonExample4 getInstance() {
        if (instance == null) { // 双重检测机制        // B
            synchronized (SingletonExample4.class) { // 同步锁
                if (instance == null) {
                    instance = new SingletonExample4(); // A - 3
                }
            }
        }
        return instance;
    }
}
