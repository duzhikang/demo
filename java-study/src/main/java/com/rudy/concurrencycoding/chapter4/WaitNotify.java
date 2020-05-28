package com.rudy.concurrencycoding.chapter4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.start();

    }

    static class Wait implements Runnable {

        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true. wait @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                        System.out.println("1234456");
                    }catch (Exception e) {

                    }
                }
                // 条件满足完成工作
                System.out.println(Thread.currentThread() + "falg is fasle. running @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "hold lock. notify@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.notifyAll();
                        flag = false;
                        SleepUtils.second(5);
                    }catch (Exception e) {

                    }
                }
                // 再次加锁
                synchronized (lock) {
                    System.out.println(Thread.currentThread() + "hold lock again. sleep@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    SleepUtils.second(5);
                }

            }
        }
    }
}
