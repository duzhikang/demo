package com.rudy.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>ClassName: TransactionListenerImpl</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/27
 * @since JDK 1.8
 */
@Slf4j
public class TransactionListenerImpl implements TransactionListener {

    private AtomicInteger transactionIndex = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /*执行本地事务，并对mq进行回复*/
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object arg) {
        log.info("executeLocalTransaction start......{}", transactionIndex.get());
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(message.getTransactionId(), status);
        return LocalTransactionState.UNKNOW;
    }

    /*“ checkLocalTransaction”方法用于检查本地事务状态并响应MQ检查请求
    。它还返回上一部分提到的三个事务状态之一。*/
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        log.info("checkLocalTransaction start-----{}", count.get());
        count.getAndIncrement();
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
                    return LocalTransactionState.UNKNOW;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
