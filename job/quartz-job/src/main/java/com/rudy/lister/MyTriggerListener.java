package com.rudy.lister;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * <p>ClassName: MyTriggerListener</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/29
 * @since JDK 1.8
 */
public class MyTriggerListener implements TriggerListener {

    private String name;

    public MyTriggerListener() {
        this.name = "myTriggerListener";
    }

    public MyTriggerListener(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @Param
     * @Description 当与监听器相关联的 Trigger 被触发，Job 上的 execute() 方法将要被执行时，Scheduler 就调用这个方法
     * @Author zhikang.du
     * @Date 2019/9/29
     * @return
     **/
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("MyTriggerListener[triggerFired] executed.......");
    }

    /**
     * @Param
     * @Description 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。
     * TriggerListener 给了一个选择去否决 Job 的执行
     * @Author zhikang.du
     * @Date 2019/9/29
     * @return
     **/
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    /**
     * @Param 
     * @Description Scheduler 调用这个方法是在 Trigger 错过触发时。
     * @Author zhikang.du
     * @Date 2019/9/29
     * @return 
     **/
    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("MyTriggerListener[triggerMisfired] executed.......");
    }

    /**
     * @Param 
     * @Description Trigger 被触发并且完成了 Job 的执行时，Scheduler 调用这个方法。
     * @Author zhikang.du
     * @Date 2019/9/29
     * @return 
     **/
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {

        System.out.println("MyTriggerListener[triggerComplete] executed.......");
    }
}
