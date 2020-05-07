package com.rudy.spring;/**
 * Created by dzk on 2020/4/25.
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName MQProducer
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/4/25
 **/
@Component
@Slf4j
public class MQProducer {

    private final DefaultMQProducer producer = new DefaultMQProducer("testRocketMQProducer");

    @PostConstruct
    public void start() {
        log.info("producer start");
        producer.setNamesrvAddr("192.168.30.188:9876");
        try {
            producer.start();
        } catch (MQClientException e) {
            log.error("MQ 启动失败： {}---{}", e.getResponseCode(), e.getErrorMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void seedMessage(String data, String topic, String tags, String keys) {
        try {
            byte[] messageBody = data.getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message message = new Message(topic, tags, keys, messageBody);
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("生产者发送消息 {} ", sendResult);

                }

                @Override
                public void onException(Throwable throwable) {
                    log.error(throwable.getMessage(), throwable);
                }
            });
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (RemotingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (MQClientException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }


    @PreDestroy
    public void stop() {
        if (producer!=null) {
            producer.shutdown();
            log.info("producer shutdown");
        }
    }

}
