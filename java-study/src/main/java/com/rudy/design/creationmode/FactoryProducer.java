package com.rudy.design.creationmode;

/**
 * <p>ClassName: FactoryProducer</p>
 * <p>Description: 厂创造器/生成器类，通过传递形状或颜色信息来获取工厂。</p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/13
 * @since JDK 1.8
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("vehicle")){
            return new VehicleFactory();
        } else if(choice.equalsIgnoreCase("color")){
            return new ColorFactory();
        }
        return null;
    }
}
