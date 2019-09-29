package com.rudy.job;

import com.rudy.lister.MyJobLister;
import com.rudy.lister.MySchedulerListener;
import com.rudy.lister.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;

/**
 * <p>ClassName: JobScheduler</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/28
 * @since JDK 1.8
 */
public class JobScheduler {

    public static void helloJobScheduler() throws SchedulerException {
        // 1.获取调度器（scheduler）,从工厂中获取调度的实例
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2.构建job实例
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job_1", "Group_1")
                .usingJobData("auth", "dzk")
                .usingJobData("date", Long.valueOf(System.currentTimeMillis()))
                .build();
        //3.设置触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "Group_1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))
                .usingJobData("name", "trigger")
                .build();

        defaultScheduler.scheduleJob(jobDetail, trigger);
        //4.启动
        defaultScheduler.start();

    }


    public static void stateJobScheduler() throws SchedulerException {
        // 1.获取调度器（scheduler）,从工厂中获取调度的实例
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2.构建job实例
        JobDetail jobDetail = JobBuilder.newJob(StateJob.class)
                .withIdentity("job_1", "Group_1")
                .usingJobData("count", 0)
                .build();
        //3.设置触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "Group_1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))
                .build();

        defaultScheduler.scheduleJob(jobDetail, trigger);
        //4.启动
        defaultScheduler.start();
    }

    public static void simpleScheduler1() throws SchedulerException {
        // 1.获取调度器（scheduler）,从工厂中获取调度的实例
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2.构建job实例
        JobDetail jobDetail = JobBuilder.newJob(StateJob.class)
                .withIdentity("job_1", "Group_1")
                .usingJobData("count", 0)
                .build();
        //3.设置触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "Group_1")
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10) //每隔10秒执行一次，重复10
                .withRepeatCount(10))
                .build();

        defaultScheduler.scheduleJob(jobDetail, trigger);
        //4.启动
        defaultScheduler.start();
    }

    public static void simpleScheduler2() throws SchedulerException {
        // 1.获取调度器（scheduler）,从工厂中获取调度的实例
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2.构建job实例
        JobDetail jobDetail = JobBuilder.newJob(StateJob.class)
                .withIdentity("job_1", "Group_1")
                .usingJobData("count", 0)
                .build();
        //3.设置触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "Group_1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever())
                .endAt(DateBuilder.dateOf(
                        22,0,0
                ))
                .build();

        defaultScheduler.scheduleJob(jobDetail, trigger);
        //4.启动
        defaultScheduler.start();
    }

    //http://cron.qqe2.com/
    public static void croeScheduler() throws SchedulerException {
        // 1.获取调度器（scheduler）,从工厂中获取调度的实例
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2.构建job实例
        JobDetail jobDetail = JobBuilder.newJob(StateJob.class)
                .withIdentity("job_1", "Group_1")
                .usingJobData("count", 0)
                .build();
        //3.设置触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "Group_1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        defaultScheduler.scheduleJob(jobDetail, trigger);
        //4.启动
        defaultScheduler.start();
    }

    public static void jobListener() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(StateJob.class)
                .withIdentity("job_1", "Group_1")
                .usingJobData("count", 0)
                .build();
        // 全局注册
        scheduler.getListenerManager().addJobListener(new MyJobLister());
        // 局部注册 1.指定具体的任务 2.指定一组任务 3.根据组的名字匹配开头和结尾或包含
        scheduler.getListenerManager().addJobListener(new MyJobLister(), KeyMatcher.keyEquals(new JobKey("job_1", "Group_1")));

        //3.设置触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "Group_1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/25 * * * * ?"))
                .build();
        MyTriggerListener triggerListener = new MyTriggerListener("aabb");
        scheduler.getListenerManager().addTriggerListener(triggerListener);
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());
        //4.启动
        scheduler.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}
