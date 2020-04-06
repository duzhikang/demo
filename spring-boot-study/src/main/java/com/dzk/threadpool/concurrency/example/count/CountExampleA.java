package com.dzk.threadpool.concurrency.example.count;/**
 * Created by dzk on 2020/2/2.
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassName CountExampleA
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/2
 **/
public class CountExampleA {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

//    public static AtomicInteger count = new AtomicInteger(0);


//    public static AtomicLong count = new AtomicLong(0);
    // LongAdder 性能更好，高并发会出现问题。
    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // Semaphore 是 synchronized 的加强版，作用是控制线程的并发数量
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("ExecutorService Exception");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count === " + count);
    }

    private static void add() {
        count.increment();
        //count.getAndIncrement();
    }
}
