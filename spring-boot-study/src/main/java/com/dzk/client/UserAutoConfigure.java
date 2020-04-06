package com.dzk.client;/**
 * Created by dzk on 2020/3/6.
 */

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName UserAutoConfigure
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/6
 **/
@Configuration
// 使使用 @ConfigurationProperties 注解的类生效。
@EnableConfigurationProperties(UserProperties.class)
// 配置类只配置@ConfigurationProperties注解，而没有使用@Component，
// 那么在IOC容器中是获取不到properties 配置文件转化的bean
public class UserAutoConfigure {

    @Bean
    @ConditionalOnProperty(prefix = "spring.user", value = "enabled", havingValue = "true")
    public UserClient userClient(UserProperties properties) {
        return new UserClient(properties);
    }
}
