package com.rudy.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>ClassName: ReenterLockDemo</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/20
 * @since JDK 1.8
 */
public class ReenterLockDemo implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;

    @Override
    public void run() {
        lock.lock();
        lock.lock();
        try {
            i++;
        }finally {
            lock.unlock();
           // lock.unlock();
        }
        lock.lock();
        try {
            i++;
        }finally {
            lock.unlock();
            // lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockDemo demo = new ReenterLockDemo();
        Thread thread = new Thread(demo);
        thread.start();
        thread.join();
        Thread thread2 = new Thread(demo);
        thread2.start();
        thread2.join();
        System.out.println(i);

    }
}
