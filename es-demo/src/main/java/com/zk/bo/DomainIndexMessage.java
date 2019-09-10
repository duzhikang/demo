package com.zk.bo;

import com.zk.basic.DocOperatorEnum;

/**
 * <p>ClassName: DomainIndexMessage</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/9
 * @since JDK 1.8
 */
public class DomainIndexMessage<T> {

    private int retry;

    private DocOperatorEnum operatorEnum;

    private T date;

    public DomainIndexMessage() {
    }

    public DomainIndexMessage(int retry, DocOperatorEnum operatorEnum, T date) {
        this.retry = retry;
        this.operatorEnum = operatorEnum;
        this.date = date;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public DocOperatorEnum getOperatorEnum() {
        return operatorEnum;
    }

    public void setOperatorEnum(DocOperatorEnum operatorEnum) {
        this.operatorEnum = operatorEnum;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DomainIndexMessage{" +
                "retry=" + retry +
                ", operatorEnum=" + operatorEnum +
                ", date=" + date +
                '}';
    }
}
