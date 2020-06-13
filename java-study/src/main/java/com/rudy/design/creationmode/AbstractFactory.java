package com.rudy.design.creationmode;

/**
 * <p>ClassName: AbstractFactory</p>
 * <p>Description: 抽象工厂 系统的产品有多于一个的产品族，而系统只消费其中某一族的产品。</p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/13
 * @since JDK 1.8
 */
public abstract class AbstractFactory {

    public abstract Color getColor(String color);

    public abstract Vehicle getVehicle(String type) ;
}
