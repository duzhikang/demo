package com.rudy.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

/**
 * <p>ClassName: TransactionProducer</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/27
 * @since JDK 1.8
 */
@Slf4j
public class TransactionProducer {

    public void producer() throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer("Transaction_G1");
        TransactionListener transactionListener = new TransactionListenerImpl();
        // 创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(2, 5,
                100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
        producer.setNamesrvAddr("192.168.234.188:9876");
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();

        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("transaction_topic",
                        tags[i%tags.length], "key_" + i,
                        ("transaction test msg ----" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                TransactionSendResult result = producer.sendMessageInTransaction(msg, null);
                log.info(result.toString());
                Thread.sleep(10000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 100000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
