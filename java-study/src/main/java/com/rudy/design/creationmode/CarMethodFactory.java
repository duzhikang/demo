package com.rudy.design.creationmode;

/**
 * <p>ClassName: CarMethodFactory</p>
 * <p>Description: 工厂方法模式 工厂类被抽象化，用于实例化特定产品类的代码被转移到实现抽象方法的子类中 </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/13
 * @since JDK 1.8
 */
public class CarMethodFactory extends Factory{

    @Override
    protected Vehicle createVehicle() {
        return new Car();
    }
}
