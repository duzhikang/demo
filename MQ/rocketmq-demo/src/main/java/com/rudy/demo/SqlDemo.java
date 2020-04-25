package com.rudy.demo;/**
 * Created by dzk on 2020/4/23.
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName SqlDemo
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/4/23
 **/
@Slf4j
public class SqlDemo {

    private static final String TOPIC = "Topic_A";

    // 消息发送默认根据主题的路由信息（主题消息队列）进行负载均衡，负载均衡机制为轮询策略。
    //
    public static void sqlPorducer() throws Exception {
        // 实例化一个生产者
        DefaultMQProducer producer = new DefaultMQProducer("group_order");
        producer.setNamesrvAddr("192.168.30.188:9876");
        // 启动实例
        producer.start();
        for (int i = 0; i < 30; i++) {
            int orderId = i % 10;
            Message msg = new Message(TOPIC, "SQL",
                    "key_" + i, ("Hello Rocket  SQL" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            msg.putUserProperty("orderStatus", String.valueOf(i));
            msg.putUserProperty("sellerId", String.valueOf(i + 30));
            SendResult send = producer.send(msg);
            log.info("send result === {}", send);
        }
        producer.shutdown();
    }

    public static void sqlComsumer() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_broadcast");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr("192.168.30.188:9876");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 需要开启对应配置
        consumer.subscribe(TOPIC, MessageSelector.bySql("(orderStatus is not null and orderStatus > 20)"));
//        consumer.subscribe(TOPIC, "SQL");
//
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                log.info("consumeMessage start ......");
                for (MessageExt msg : list) {
                    System.out.println(msg.getTopic());
                    System.out.println(msg.getTags());
                    try {
                        System.out.println(new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }

    public static void main(String[] args) {
        try {
             sqlPorducer();
//           sqlComsumer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
