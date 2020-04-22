package com.rudy.boradconst;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>ClassName: BroadcastProducer</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/27
 * @since JDK 1.8
 */
@Slf4j
public class BroadcastProducer {

    private static final String TOPIC = "Topic_broadcast";

    private static final String TAG = "tag_a";

    public static void producer() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("broadcast_group");
        producer.setNamesrvAddr("192.168.30.188:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("Topic_broadcast", "tag_a", "order_id" + i,
                    ("broadcast message -----" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = producer.send(msg);
            System.out.println(send);
        }

        producer.shutdown();
    }

    // 批量发送消息
    public static void batchSeed() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("broadcast_group");
        producer.setNamesrvAddr("192.168.30.188:9876");
        producer.start();
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messages.add(new Message(TOPIC, TAG, "ID_" + i,
                    ("hello world!!!" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)));

        }
        SendResult send = producer.send(messages);
        if (log.isInfoEnabled()) {
            log.info("send result === {}", send);
        }
        producer.shutdown();
    }

    public static void main(String[] args) {
        try {
            producer();
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
