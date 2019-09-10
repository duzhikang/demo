package com.zk.basic;

/**
 * <p>ClassName: DocOperatorEnum</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/9
 * @since JDK 1.8
 */
public enum DocOperatorEnum {

    INDEX("index"),
    UPDATE("update"),
    DELETE("delete");

    private String str;

    DocOperatorEnum(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
