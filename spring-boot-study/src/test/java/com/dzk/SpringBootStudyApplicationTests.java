package com.dzk;

import com.dzk.client.UserClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootStudyApplicationTests {

	@Autowired
	private UserClient userClient;

	@Test
	public void contextLoads() {
		System.out.println(userClient.getName());
	}

}
