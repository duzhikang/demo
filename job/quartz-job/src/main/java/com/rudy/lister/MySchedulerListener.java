package com.rudy.lister;

import org.quartz.*;

/**
 * <p>ClassName: MySchedulerListener</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/29
 * @since JDK 1.8
 */
public class MySchedulerListener implements SchedulerListener {
    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("任务被部署时被执行");
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("任务卸载时执行");
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("任务完成了它的使命，光荣退休时被执行");
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println("TriggerKey:用这个方法是发生在一个 Trigger 或 Trigger 组被暂停时执行");
    }

    @Override
    public void triggersPaused(String s) {
        System.out.println("s:用这个方法是发生在一个 Trigger 或 Trigger 组被暂停时执行");
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        System.out.println("triggerKey Scheduler 调用这个方法是发生成一个 Trigger 或 Trigger 组从暂停中恢复时。");
    }

    @Override
    public void triggersResumed(String s) {
        System.out.println("s Scheduler 调用这个方法是发生成一个 Trigger 或 Trigger 组从暂停中恢复时。");
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        System.out.println("一个新的任务被动态添加时执行");
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        System.out.println("任务被卸载时被执行");
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        System.out.println("当一个或一组 JobDetail 暂停时执行");
    }

    @Override
    public void jobsPaused(String s) {
        System.out.println("当一个或一组 JobDetail 暂停时执行");
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        System.out.println("当一个或一组 JobDetail 恢复时执行");
    }

    @Override
    public void jobsResumed(String s) {
        System.out.println("当一个或一组 JobDetail 恢复时执行");
    }

    @Override
    public void schedulerError(String s, SchedulerException e) {
        System.out.println("在 Scheduler 的正常运行期间产生一个严重错误时执行");
    }

    @Override
    public void schedulerInStandbyMode() {
        System.out.println("scheduler 处于standby模式时执行");
    }

    @Override
    public void schedulerStarted() {
        System.out.println("scheduler开启时执行");
    }

    @Override
    public void schedulerStarting() {
        System.out.println("scheduler开启中执行");
    }

    @Override
    public void schedulerShutdown() {
        System.out.println("scheduler关闭时执行");
    }

    @Override
    public void schedulerShuttingdown() {
        System.out.println("scheduler关闭中执行");
    }

    @Override
    public void schedulingDataCleared() {
        System.out.println("scheduler数据清除时执行");
    }
}
