package com.rudy.boradconst;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

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
public class BroadcastProducer {

    public void producer() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("broadcast_group");
        producer.setNamesrvAddr("192.168.234.188:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("Topic_broadcast", "tag_a", "order_id" + i,
                    ("broadcast message -----" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = producer.send(msg);
            System.out.println(send);
        }

        producer.shutdown();
    }
}
