package com.rudy.thread.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>ClassName: CyclicBarrierDemo</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/7/4
 * @since JDK 1.8
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int n = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n);
        for (int i = 0; i < n; i++) {
            new BusinessThread(cyclicBarrier).start();
        }
    }

    // 业务线程
    static class  BusinessThread extends Thread {
        private CyclicBarrier cyclicBarrier;

        public BusinessThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000); // 执行业务逻辑
                System.out.println("first task complate!!!!");
                // 业务线程执行完成，等待其他线程准备工作完成
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有都执行完成 开始执行下一任务");
        }
    }
}
