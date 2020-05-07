package com.rudy.filter;/**
 * Created by dzk on 2020/4/25.
 */

import org.apache.rocketmq.common.filter.FilterContext;
import org.apache.rocketmq.common.filter.MessageFilter;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @ClassName MessageFilterImpl
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/4/25
 **/
public class MessageFilterImpl implements MessageFilter {
    @Override
    public boolean match(MessageExt messageExt, FilterContext filterContext) {
        String property = messageExt.getProperty("sellerId");
        System.out.println("filter...." + property);
        if (property != null) {
            if (property.equals("21")) {
                return true;
            }
        }
        return false;
    }
}
