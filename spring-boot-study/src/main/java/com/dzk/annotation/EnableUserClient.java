package com.dzk.annotation;/**
 * Created by dzk on 2020/3/6.
 */

import com.dzk.client.UserAutoConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableUserClient
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/6
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({UserAutoConfigure.class})
public @interface EnableUserClient {
}
