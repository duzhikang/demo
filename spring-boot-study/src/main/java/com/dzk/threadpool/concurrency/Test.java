package com.dzk.threadpool.concurrency;/**
 * Created by dzk on 2020/1/31.
 */

import java.util.concurrent.*;

/**
 * @ClassName Test
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/1/31
 **/
public class Test {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        // ArrayBlockingQueue 队列深度不够回包RejectedExecutionException
        ExecutorService executorService = new ThreadPoolExecutor(2, 5,
                100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-ThreadPoolExecutorDemo-thread");
                        return thread;
                    }
                });
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
        // shutdown调用后，不可以再submit新的task，已经submit的将继续执行。

        // shutdownNow试图停止当前正执行的task，并返回尚未执行的task的list
        executorService.shutdown();
        System.out.println("count ++" + count);
    }

    private static void add() {
        count++;
    }
}
