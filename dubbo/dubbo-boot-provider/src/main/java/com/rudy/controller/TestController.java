package com.rudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>ClassName: TestController</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/15
 * @since JDK 1.8
 */
@RestController
public class TestController {

    @GetMapping(value = "/test")
    public void test() {
        System.out.println("tstesetst");
    }
}
