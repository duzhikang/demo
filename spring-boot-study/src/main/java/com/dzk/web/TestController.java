package com.dzk.web;/**
 * Created by dzk on 2020/1/31.
 */

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.dzk.base.ResponseData;
import com.dzk.base.ResponseDataUtils;
import com.dzk.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/1/31
 **/
@RestController
public class TestController {

    @ApolloConfig
    private Config config;

    @GetMapping("apollo")
    public String apollo() {
        String property = config.getProperty("name", "sss");
        return property;
    }

    @GetMapping("test")
    public String test() {
        System.out.println(Thread.currentThread().getName() );
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test";
    }

    @GetMapping("exce")
    public ResponseData exception() {
        sss();
        ResponseData success = ResponseDataUtils.success();
        return success;
    }

    public void sss() {
        throw new CustomException("error;");
    }
}
