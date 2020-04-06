package com.dzk.base;/**
 * Created by dzk on 2020/3/5.
 */

/**
 * @ClassName ResponseDataUtils
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/5
 **/
public class ResponseDataUtils {

    public static ResponseData success(Object data) {
        ResponseData result = new ResponseData();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static ResponseData success() {
        ResponseData result = new ResponseData();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    public static ResponseData error(String msg) {
        ResponseData result = new ResponseData();
        result.setCode(1001);
        result.setMsg(msg);
        return result;
    }


}
