package com.rudy.concurrencycoding.chapter4;

import java.util.concurrent.TimeUnit;

public class Interrupted {

    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(), "BusyRunner");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sleepThread.interrupt();
        busyThread.interrupt();
        // st中断标识位被清除
        System.out.println("st interrupted is" + sleepThread.isInterrupted());
        System.out.println("bt interrupted is" + busyThread.isInterrupted());
        // 防止sleepThread budyThread 立刻退出
        SleepUtils.second(2);
    }


    static class SleepRunner implements Runnable {

        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        public void run () {
            while (true) {

            }
        }
    }
}
