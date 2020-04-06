package com.dzk.client;/**
 * Created by dzk on 2020/3/6.
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName UserProperties
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/6
 **/
@ConfigurationProperties("spring.user")
public class UserProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
