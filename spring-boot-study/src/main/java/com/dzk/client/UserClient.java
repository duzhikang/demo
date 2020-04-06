package com.dzk.client;/**
 * Created by dzk on 2020/3/6.
 */

/**
 * @ClassName UserClient
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/6
 **/
public class UserClient {

    private UserProperties userProperties;

    public UserClient() {}

    public UserClient(UserProperties properties){
        this.userProperties = properties;
    }

    public String getName() {
        return userProperties.getName();
    }
}
