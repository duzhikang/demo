package com.rudy.spring;/**
 * Created by dzk on 2020/4/25.
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @ClassName MQPushConsumer
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/4/25
 **/
@Component
@Slf4j
public class MQPushConsumer implements MessageListenerConcurrently {

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestRocketMQPushConsumer");

    @PostConstruct
    public void start() {
        try {

            log.info("MQ: 启动消费者");
            consumer.setNamesrvAddr("192.168.30.188:9876");
            // 从队列头部开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            // 集群消费模式
            consumer.setMessageModel(MessageModel.CLUSTERING);
            // 订阅主题
            consumer.subscribe("Topic_A", "*");
            // 注册消息监听器
            consumer.registerMessageListener(this);
            // 启动消费端
            consumer.start();
        }catch (MQClientException e) {
            log.error("启动消费者失败{} {}", e.getResponseCode(), e.getErrorMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        int index = 0;
        try {
            for (; index < list.size(); index ++) {
                MessageExt messageExt = list.get(index);
                String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                log.info("MQ 新接受的消息： {} - {} - {} - {} - {}", messageExt.getMsgId(),
                        messageExt.getTopic(), messageExt.getTags(), messageExt.getKeys(), messageBody);

            }
        }catch (Exception e) {
            log.error("消息消费失败 {}",  e.getMessage());
        }finally {
            if (index < list.size()) {
                consumeConcurrentlyContext.setAckIndex(index + 1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @PreDestroy
    public void stop() {
        if (consumer != null) {
            consumer.shutdown();
            log.error("消费者关闭了");
        }
    }
}
