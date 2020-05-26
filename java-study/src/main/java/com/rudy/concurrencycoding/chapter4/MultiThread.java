package com.rudy.concurrencycoding.chapter4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

public class MultiThread {

    public static void main(String[] args) {
        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        Arrays.stream(threadInfos).forEach(var -> {
            System.out.println("[" + var.getThreadId()+ "]" + var.getThreadName());
        });
        // [6]Monitor Ctrl-Break
        //[5]Attach Listener
        //[4]Signal Dispatcher
        //[3]Finalizer
        //[2]Reference Handler
        //[1]main 主线程
    }
}
