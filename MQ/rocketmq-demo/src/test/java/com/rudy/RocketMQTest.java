package com.rudy;/**
 * Created by dzk on 2020/4/22.
 */

import com.rudy.boradconst.BroadcastProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName RocketMQTest
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/4/22
 **/
public class RocketMQTest extends ApplicationTests {

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
}
