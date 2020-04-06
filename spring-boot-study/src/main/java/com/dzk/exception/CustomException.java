package com.dzk.exception;/**
 * Created by dzk on 2020/3/5.
 */

/**
 * @ClassName CustomException
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/5
 **/
public class CustomException extends RuntimeException{

    private String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
