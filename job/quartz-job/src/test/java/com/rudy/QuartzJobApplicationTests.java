package com.rudy;

import com.rudy.job.JobScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzJobApplicationTests {

	@Test
	public void contextLoads() {

		try {
			JobScheduler.helloJobScheduler();
			Thread.sleep(30000);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void stateJob() {
		try {
			JobScheduler.jobListener();
			Thread.sleep(30000);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
