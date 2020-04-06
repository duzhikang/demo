package com.dzk.threadpool.concurrency.example.atomic.singleton;/**
 * Created by dzk on 2020/2/6.
 */

/**
 * @ClassName SingletonExample3
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/6
 **/
public class SingletonExample3 {

    // 私有构造函数
    private SingletonExample3() {

    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态的工厂方法，性能不好
    public static synchronized SingletonExample3 getInstance() {
       /* if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;*/
        // 线程不安全
        // 1、memory = allocate() 分配对象的内存空间
        // 2、ctorInstance() 初始化对象
        // 3、instance = memory 设置instance指向刚分配的内存

        // JVM和cpu优化，发生了指令重排

        // 1、memory = allocate() 分配对象的内存空间
        // 3、instance = memory 设置instance指向刚分配的内存
        // 2、ctorInstance() 初始化对象
        if (instance == null) { // 双重检测机制        // B
            synchronized (SingletonExample3.class) { // 同步锁
                if (instance == null) {
                    instance = new SingletonExample3(); // A - 3
                }
            }
        }
        return instance;
    }
}
