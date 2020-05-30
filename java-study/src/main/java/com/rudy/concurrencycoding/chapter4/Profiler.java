package com.rudy.concurrencycoding.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * <p>ClassName: Profiler</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/30
 * @since JDK 1.8
 */
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL =  new ThreadLocal<Long>() {

        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        long end = Profiler.end();
        System.out.println(end);
    }
}
