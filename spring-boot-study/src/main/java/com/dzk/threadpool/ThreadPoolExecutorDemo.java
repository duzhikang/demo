package com.dzk.threadpool;/**
 * Created by dzk on 2020/1/31.
 */

import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolExecutorDemo
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/1/31
 **/
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {



        // 创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(2, 5,
                100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-ThreadPoolExecutorDemo-thread");
                        return thread;
                    }
                });

        //
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {

                    System.out.println("this is good ");
                }
            });
        }

        executorService.shutdown();
    }
}
