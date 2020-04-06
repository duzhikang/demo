package com.dzk.threadpool.concurrency.example.sync;/**
 * Created by dzk on 2020/2/2.
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SynchronizedDemoA
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/2
 **/
@Slf4j
public class SynchronizedDemoA {

    // 修饰一个代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
               // log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 修饰一个方法
    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
           // log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {

        SynchronizedDemoA a = new SynchronizedDemoA();
        SynchronizedDemoA b = new SynchronizedDemoA();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> {
            a.test2(1);
        });

        executor.submit(() -> {
            b.test2(2);
        });
    }

}
