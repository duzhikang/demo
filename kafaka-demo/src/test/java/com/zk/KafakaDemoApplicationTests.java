package com.zk;

import com.zk.server.ProducerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafakaDemoApplicationTests {

	@Autowired
	private ProducerServer producer;

	@Test
	public void contextLoads() {

		producer.send("测试消息发送");
	}

}
