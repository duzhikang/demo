package com.rudy.design.creationmode;

/**
 * <p>ClassName: VehicleStaticFactory</p>
 * <p>Description: 静态工厂模式</p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/13
 * @since JDK 1.8
 */
public class VehicleStaticFactory {

    public enum VehicleType{
        BIKE,CAR,TRUCK;
    }

    public static Vehicle create(VehicleType type) {
        if (type.equals(VehicleType.BIKE)) {
            return new Bike();
        }
        if (type.equals(VehicleType.CAR)) {
            return new Car();
        }
        return null;
    }
}
