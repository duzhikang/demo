package com.rudy.design.creationmode;

/**
 * <p>ClassName: ColorFactory</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/13
 * @since JDK 1.8
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("red")){
            return new Red();
        } else if(color.equalsIgnoreCase("blue")){
            return new Blue();
        }
        return null;
    }

    @Override
    public Vehicle getVehicle(String type) {
        return null;
    }
}
