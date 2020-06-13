package com.rudy.design.creationmode;

/**
 * <p>ClassName: Bike</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/13
 * @since JDK 1.8
 */
public class Bike extends Vehicle {


    @Override
    public void create() {
        System.out.println("create bike");
    }
}
