package service;

import bean.UserAddress;

import java.util.List;

/**
 * <p>ClassName: OrderService</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/15
 * @since JDK 1.8
 */
public interface OrderService {


    /**
     * 初始化订单
     * @param userId
     */
    public List<UserAddress> initOrder(String userId);
}
