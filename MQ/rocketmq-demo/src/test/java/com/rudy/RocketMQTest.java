package com.rudy;/**
 * Created by dzk on 2020/4/22.
 */

import com.rudy.boradconst.BroadcastProducer;
import com.rudy.spring.MQProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName RocketMQTest
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/4/22
 **/
public class RocketMQTest extends ApplicationTests {

    @Resource
    private MQProducer producer;

    // 批量发送消息
    @Test
    public void batchSeed() {
        try {
            BroadcastProducer.batchSeed();
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

    @Test
    public void producer() {
        for (int i = 0; i < 100; i++) {
            producer.seedMessage("test " + i, "Topic_A", "spring", "key_" + i);
            try {
                Thread.sleep(10 * 10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
