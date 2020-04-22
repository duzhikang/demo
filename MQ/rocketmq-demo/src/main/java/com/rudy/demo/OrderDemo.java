package com.rudy.demo;/**
 * Created by dzk on 2020/4/22.
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
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
 * @ClassName OrderDemo
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/4/22
 **/
@Slf4j
public class OrderDemo {

    private static final String TOPIC = "Topic_A";

    // 消息发送默认根据主题的路由信息（主题消息队列）进行负载均衡，负载均衡机制为轮询策略。
    //
    public static void orderPorducer() throws Exception {
        // 实例化一个生产者
        DefaultMQProducer producer = new DefaultMQProducer("group_order");
        producer.setNamesrvAddr("192.168.30.188:9876");
        // 启动实例
        producer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 100; i++) {
            int orderId = i % 10;
            Message msg = new Message(TOPIC, tags[i % tags.length],
                    "key_"+ i, ("Hello Rocket" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = producer.send(msg, new MessageQueueSelector() {

                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Integer id = (Integer) o;
                    Integer index = id % list.size();
                    return list.get(index);
                }
            }, orderId);
            log.info("send result === {}", send);
        }
        producer.shutdown();
    }

    public static void orderComsumer() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_broadcast");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr("192.168.30.188:9876");
        //
        consumer.subscribe(TOPIC, "TagA || TagC || TagD");

        consumer.registerMessageListener(new MessageListenerOrderly() {

            AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(false);
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                this.consumeTimes.incrementAndGet();
                if ((this.consumeTimes.get() % 2) == 0) {
                    return ConsumeOrderlyStatus.SUCCESS;
                } else if ((this.consumeTimes.get() % 3) == 0) {
                    return ConsumeOrderlyStatus.ROLLBACK;
                } else if ((this.consumeTimes.get() % 4) == 0) {
                    return ConsumeOrderlyStatus.COMMIT;
                } else if ((this.consumeTimes.get() % 5) == 0) {
                    context.setSuspendCurrentQueueTimeMillis(3000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
    }

    public static void main(String[] args) {
        try {
           // orderPorducer();
            orderComsumer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
