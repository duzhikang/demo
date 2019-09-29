package com.rudy.job;

import org.quartz.*;

/**
 * <p>ClassName: StateJob</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/29
 * @since JDK 1.8
 */
// 多次调用job时候，会对job进行持久化，保存数据信息，有状态job 和无状态job
@PersistJobDataAfterExecution
public class StateJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Integer count = (Integer) jobDataMap.get("count");
        ++count;
        System.out.println(count);
        jobDataMap.put("count", count);
    }
}
