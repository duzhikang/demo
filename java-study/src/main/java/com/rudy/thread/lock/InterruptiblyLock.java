package com.rudy.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptiblyLock {
    public ReentrantLock lock1 = new ReentrantLock(); //第一把锁Lock1
    public ReentrantLock lock2 = new ReentrantLock(); // 第二把锁Lock2

    public Thread lock1() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.lockInterruptibly(); // 如果当前线程未被中断，则获取锁

                    try {
                        Thread.sleep(500); // 执行业务
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock2.lockInterruptibly(); //中断信号 释放锁
                    System.out.println(Thread.currentThread().getName() + ",1执行完毕！");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 在业务逻辑执行结束之后，检查当前线程是否持有该锁，如果持有释放该锁
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }
                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                    System.out.println(Thread.currentThread().getName() + ",1完毕！");
                }
            }
        });
        t.start();
        return t;
    }

    public Thread lock2() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.lockInterruptibly(); // 如果当前线程未被中断，则获取锁

                    try {
                        Thread.sleep(500); // 执行业务
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + ",执行完毕！");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }
                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                    System.out.println(Thread.currentThread().getName() + ",完毕！");
                }
            }
        });
        t.start();
        return t;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        InterruptiblyLock interruptiblyLock = new InterruptiblyLock();
        Thread thread = interruptiblyLock.lock1();
        Thread thread1 = interruptiblyLock.lock2();
        // 自选一段时间，如果等待时间长了，则可能发生死锁，主动中断并释放锁



        while (true) {
            if (System.currentTimeMillis() - time >= 3000) {
                thread1.interrupt();
            }
        }
    }
}
