package com.dzk.exception;/**
 * Created by dzk on 2020/3/5.
 */

import com.dzk.base.ResponseData;
import com.dzk.base.ResponseDataUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName ExceptionHandler
 * @Description 异常处理
 * @Author dzk
 * @Version v1
 * @Date 2020/3/5
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseData exceptionHander(Exception e, HttpServletResponse response) {
        response.setStatus(500);
        return ResponseDataUtils.error(e.getMessage());
    }
}
