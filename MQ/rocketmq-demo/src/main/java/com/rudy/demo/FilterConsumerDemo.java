package com.rudy.demo;/**
 * Created by dzk on 2020/4/25.
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName FilterConsumerDemo
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/4/25
 **/
@Slf4j
public class FilterConsumerDemo {

    // 4.3版本后 #开启对filter的支持
    // enablePropertyFilter=true
    // 在4.3版本以前的过滤器是会在broker的服务器上运行一个filterServer的进程，并在broker的配置中加上：filterServerNums=1。
    //  最后编写代码时会写一个实现MessageFilter接口的类，然后消费者类中编写添加如下：
    public static void consumerWithFilter() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_filter");
        System.out.println(FilterConsumerDemo.class.getClassLoader());
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader.getResource(""));
        // F:\git-code\demo\MQ\rocketmq-demo\src\main\java\com\rudy\demo\FilterConsumerDemo.java
//        File file = new File(String.valueOf(contextClassLoader.getResource("")));
        File file = new File("F:\\git-code\\demo\\MQ\\rocketmq-demo\\src\\main\\java\\com\\rudy\\filter\\MessageFilterImpl.java");
        System.out.println(file.getPath());
        String filterCode = null;
        try {
            filterCode = MixAll.file2String(file);
        } catch (IOException e) {
            System.out.println("**************** file2String errror" + e.getMessage());
        }
        try {
            consumer.subscribe("Topic_A", "com.rudy.filter.MessageFilterImpl", filterCode);
        } catch (MQClientException e) {
            System.out.println("**************** subscribe errror" + e.getMessage());
        }

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
            consumerWithFilter();
        } catch (MQClientException e) {
            System.out.println(e.getErrorMessage());
            e.printStackTrace();
        }
    }
}
