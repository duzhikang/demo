package com.zk;

import com.zk.consumer.TestConsumer;
import com.zk.producer.TestProducer;
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


	@Test
	public void send1() {
		TestProducer obj = new TestProducer();
		obj.producer();
	}

	@Test
	public void consumer() {
		TestConsumer obj = new TestConsumer();
		obj.consumer();
	}

}
