package com.zk.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>ClassName: ApplicationConfig</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/4
 * @since JDK 1.8
 */
@Configuration
public class ApplicationConfig {

    /**
     * Bean Util
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
