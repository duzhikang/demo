package com.rudy.controller;

import bean.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

import java.util.List;

/**
 * <p>ClassName: OrderController</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/15
 * @since JDK 1.8
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping("/initOrder")
    public List<UserAddress>  order() {
        System.out.println("_____start_______");
        List<UserAddress> userAddresses = orderService.initOrder("123");
        return userAddresses;
    }
}
