package com.rudy.concurrencycoding.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>ClassName: Counter</p>
 * <p>Description: 实现了一个基于CAS线程安全的计数器方法safeCount和一个非线程安全的计数器count </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/18
 * @since JDK 1.8
 */
public class Counter {

    // 用原子方式更新的int值
    private AtomicInteger atomicI = new AtomicInteger(0);

    private int i = 0;

    private void  safeCount() {
        for (;;) {
            // atomicI.incrementAndGet();
            int i = atomicI.get();
            boolean flag = atomicI.compareAndSet(i, ++i);
            if (flag) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() ->  {
                for (int j = 0; j < 10000; j++) {
                    counter.count();
                    counter.safeCount();
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        /*try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.i);
        System.out.println(counter.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);*/
        for (Thread t : ts) {
            try {
                // thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
                // 子线程执行完毕才会执行住线程
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(counter.i);
            System.out.println(counter.atomicI.get());
            System.out.println(System.currentTimeMillis() - start);
        }

    }
}
