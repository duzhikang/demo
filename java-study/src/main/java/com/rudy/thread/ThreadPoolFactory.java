package com.rudy.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * <p>ClassName: ThreadPoolFactory</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/7
 * @since JDK 1.8
 */
@Slf4j
public class ThreadPoolFactory {

    /**
     * 单个线程的线程池，即线程池中每次只有一个线程工作，单线程串行执行任务.
     * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
     * @return
     */
    public static ExecutorService singleThreadExecutor() {
        // queue LinkedBlockingQueue
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        return singleThreadExecutor;
    }
    /**
     *  固定数量的线程池，没提交一个任务就是一个线程，直到达到线程池的最大数量，然后后面进入等待队列直到前面的任务完成才继续执行.
     *  如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
     */
    public static ExecutorService fixedThreadPool() {
        // queue LinkedBlockingQueue
        ExecutorService pool= Executors.newFixedThreadPool(2);
        return pool;
    }

    /**
     * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
     *
     * 那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
     * 此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
     * @return
     */
    public static ExecutorService cacheThreadPool() {
        // queue SynchronousQueue
        ExecutorService pool= Executors.newCachedThreadPool();
        return pool;
    }

    /**
     * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
     */
    @Test
    public void scheduledThreadPool() {
        ScheduledExecutorService pool=  Executors.newScheduledThreadPool(1);
        pool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule run");
            }
        }, 3, TimeUnit.SECONDS);
        /*pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule run");
            }
        }, 1,3, TimeUnit.SECONDS);*/
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void  threadPoolExecutor() {
        // 创建线程池
        /**
         * corePoolSize:  池中所保存的线程数，包括空闲线程
         *
         * maximumPoolSize-池中允许的最大线程数。
         *
         * keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
         *
         * unit - keepAliveTime 参数的时间单位。
         *
         * workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute方法提交的 Runnable任务。
         *   - LinkedBlockingQueue 无界的。
         *   - SynchronousQueue 该QUEUE中，每个插入操作必须等待另一个线程的对应移除操作。
         *   - ArrayBlockingQueue 有界队列.防止资源耗尽，CPU使用率较高
         *
         * threadFactory - 执行程序创建新线程时使用的工厂。
         *
         * handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。
         *
         * ThreadPoolExecutor是Executors类的底层实现。
         * - CallerRunsPolicy 由调用线程处理该任务
         * - AbortPolicy 丢弃任务并抛出RejectedExecutionException异常。（默认）
         * - DiscardPolicy 也是丢弃任务，但是不抛出异常
         * - DiscardOldestPolicy 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         */
        ExecutorService executorService = new ThreadPoolExecutor(2, 5,
                100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("ThreadPoolFactory-thread");
                        return thread;
                    }
                });
    }

    @Test
    public void testfixedThreadPool1() {
        ExecutorService executorService = fixedThreadPool();
        for (int i = 0; i < 20; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " index = " + index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("ssss");
                    }
                }
            });
        }
        executorService.shutdown();
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleThreadExecutor2() {
        ExecutorService executorService = ThreadPoolFactory.singleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " index = " + index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("ssss");
                    }
                }
            });
        }
        // executorService.shutdownNow();
         executorService.shutdown();
        /*try {
            // shutdown()不阻塞。
            // awaitTermination()是阻塞的，返回结果是线程池是否已停止（true/false）
            // 【从强到弱】 依次是：shuntdownNow() > shutdown() > awaitTermination()
            boolean flag = executorService.awaitTermination(2 * 1000, TimeUnit.MILLISECONDS);
            System.out.println(flag);
            if (!flag) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleThreadExecutor() {
        ExecutorService executorService = ThreadPoolFactory.singleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // Thread.currentThread().setName("Thread i = " + index);
                    System.out.println(Thread.currentThread().getName() + " index = " + index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("ssss");
                    }
                }
            });
            if (i==8) {
                // 执行shutdown()方法后，线程池状态变为SHUTDOWN状态，此时，不能再往线程池中添加新任务，否则会抛出RejectedExecutionException异常
                // 执行shutdownNow()方法后，线程池状态会立刻变成STOP状态，并试图停止所有正在执行的线程，不再处理还在池队列中等待的任务，会返回那些未执行的任务。
                executorService.shutdownNow();
            }
        }
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*ThreadPoolFactory.singleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                // Thread.currentThread().setName("Thread i = " + index);
                System.out.println(Thread.currentThread().getName() + " index = " + 1222);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("ssss");
                }
            }
        });

        ThreadPoolFactory.singleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                // Thread.currentThread().setName("Thread i = " + index);
                System.out.println(Thread.currentThread().getName() + " index = " + 1222);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("ssss");
                }
            }
        });*/

    }

}
