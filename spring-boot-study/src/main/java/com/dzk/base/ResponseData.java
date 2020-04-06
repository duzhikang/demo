package com.dzk.base;/**
 * Created by dzk on 2020/3/5.
 */

import java.io.Serializable;

/**
 * @ClassName ResponseData
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/5
 **/
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = -4955234281309925927L;

    /**
     * <p>
     * 错误码.
     * </P>
     */
    private Integer code;

    /**
     * * <P>
     * 提示信息.
     * </P>
     */
    private String msg;

    /**
     * * <P>
     * 具体的内容.
     * </P>
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
