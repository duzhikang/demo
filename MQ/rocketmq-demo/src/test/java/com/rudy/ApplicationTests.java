package com.rudy;

import com.rudy.boradconst.BroadcastProducer;
import com.rudy.transaction.TransactionProducer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void syncProducer() throws Exception {
		/*1.创建DefaultMQProducer
          2 .设置Namesrv地址
          3.开启DefaultMQProducer
          4.创建消息Message
          5.发送消息
          6.关闭DefaultMQProducer*/
		DefaultMQProducer producer = new DefaultMQProducer("unique_group_A");
		producer.setNamesrvAddr("192.168.234.188:9876");
		// 开启生产者
		producer.start();
		for (int i = 0; i < 10; i++) {
			Message msg = new Message("TopicTest"  /*Topic主题*/ ,
					"TagA"  /*Tag标签，用于消息过滤*/ ,
					// "keys_1", //keys 消息的唯一值
					("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
					 /*Message body */
			);
			// SendResult sendResult = producer.send(msg);
			SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
				@Override
				public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
					// 获取队列指定下标
					Integer index = (Integer) o;
					return list.get(index);
				}
			}, 0);
			System.out.println(sendResult);
		}
		/*Message msg = new Message("TopicTest",
				"TagA",
				("Hello RocketMQ " ).getBytes(RemotingHelper.DEFAULT_CHARSET)
		);
		SendResult sendResult = producer.send(msg);
		System.out.println(sendResult);*/
		producer.shutdown();
	}

	@Test
	public void orderedConsumer() throws MQClientException {
		//1.创建DefaultMQPushConsumer
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Comsumer_A");
		//consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		//2.设置namesrv地址
		consumer.setNamesrvAddr("192.168.234.188:9876");
		//3.设置读取消息的topic tag 用来过滤消息的
		consumer.subscribe("TopicTest",
				    "TagA"
				);
		//4.创建消息监听MessageListener
		/*consumer.setMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : list) {
					try {
						System.out.println(msg.getTopic());
						System.out.println(msg.getTags());
						System.out.println(new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						return ConsumeConcurrentlyStatus.RECONSUME_LATER;
					}
					System.out.println("-----------------------------------------------");
				}
				//返回消息状态
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});*/
		//有序消费
		consumer.setMessageListener(new MessageListenerOrderly() {
			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
				System.out.println("start.....");
				for (MessageExt msg : list) {
					try {
						System.out.println(msg.getTopic());
						System.out.println(msg.getTags());
						System.out.println(new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
					}
					System.out.println("-----------------------------------------------");
				}
				//返回消息状态
				return ConsumeOrderlyStatus.SUCCESS;
			}
		});

		//启动消费者监听
		consumer.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void transactionProducer() throws MQClientException {
		TransactionProducer instance = new TransactionProducer();
		instance.producer();
	}

	@Test
	public void broadcastProducer() {
		BroadcastProducer instance = new BroadcastProducer();
		try {
			instance.producer();
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		}
	}

}
