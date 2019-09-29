package com.rudy.job;

import org.quartz.*;

/**
 * <p>ClassName: HelloJob</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/28
 * @since JDK 1.8
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("Hello!  HelloJob is executing.");

        // 获取jobdetail
        JobKey key = context.getJobDetail().getKey();
        System.out.println("key---" +key.getName() + "---" + key.getGroup());
        // 获取detail map 里面的数据
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        System.out.println(jobDataMap.get("auth"));
        System.out.println(jobDataMap.get("date"));
        //获取trigger
        Trigger trigger = context.getTrigger();
        JobDataMap dataMap = trigger.getJobDataMap();
        System.out.println(dataMap.get("name"));
        System.out.println("_________________________");
        System.out.println();
    }
}
