package com.rudy.lister;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * <p>ClassName: MyJobLister</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/29
 * @since JDK 1.8
 */
public class MyJobLister implements JobListener{
    private String name;

    public MyJobLister() {
        this.name = "MyJobLister";
    }

    public MyJobLister(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /*任务执行之前执行*/
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

        System.out.println("MyJobListener.jobToBeExecuted()");
    }

    /**
     * @Param
     * @Description 这个方法正常情况下不执行, 但是如果当TriggerListener中的vetoJobExecution方法返回true时, 那么执行这个方法.
     * 需要注意的是 如果方法(2)执行 那么(1),(3)这个俩个方法不会执行,因为任务被终止了嘛.
     * @Author zhikang.du
     * @Date 2019/9/29
     * @return
     **/
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("TriggerListener.vetoJobExecution return value is true");
    }

    /**
     * @Param
     * @Description 任务执行完成后执行,jobException如果它不为空则说明任务在执行过程中出现了异常
     * @Author zhikang.du
     * @Date 2019/9/29
     * @return
     **/
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println(e == null);
        System.out.println("MyJobListener.jobWasExecuted()");
    }
}
