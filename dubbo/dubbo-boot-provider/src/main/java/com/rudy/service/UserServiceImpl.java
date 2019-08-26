package com.rudy.service;

import bean.UserAddress;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * <p>ClassName: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/15
 * @since JDK 1.8
 */
@Lazy(false)
@Service//暴露服务 dubbo service注解
@Component
public class UserServiceImpl implements UserService {

    @Override
    public List<UserAddress> getUserAddressList(String s) {
        // TODO Auto-generated method stub
        System.out.println("UserServiceImpl..3.....");
        UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
        UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        /*if(Math.random()>0.5) {
            throw new RuntimeException();
        }*/
        return Arrays.asList(address1,address2);
    }
}
