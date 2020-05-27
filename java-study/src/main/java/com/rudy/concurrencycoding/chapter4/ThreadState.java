package com.rudy.concurrencycoding.chapter4;

public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new TimeWaiting(), "WaitingThread").start();
        // 使用两个Blocked线程，一个获取获取锁，另一个被阻塞
        new Thread(new Blocked(), "BlockThread-1").start();
        new Thread(new Blocked(), "BlockThrad-2").start();
    }

    static class TimeWaiting implements Runnable {
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    // sleep是Thread类的方法，导致此线程暂停执行指定时间，给其他线程执行机会，但是依然保持着监控状态，过了指定时间会自动恢复，调用sleep方法不会释放锁对象。
    // 当一个线程执行到wait()方法时，它就进入到一个和该对象相关的等待池中，同时释放了锁对象，等待期间可以调用里面的同步方法，其他线程可以访问，等待时不拥有CPU的执行权，否则其他线程无法获取执行权。
    // 该线程在Waiting.class实例上等待
    static class Waiting implements Runnable {
        public void run () {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 该线程在Blocked.class实例加锁后，不会释放该锁
    static class Blocked implements Runnable{

        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }
}
