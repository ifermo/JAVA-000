package pers.qingxuan.beans.staticfactory;

/**
 * <p> 工厂类
 *
 * @author : QingXuan
 * @since Created in 下午11:03 2020/11/16
 */
public class CarStaticFactory {

    public static Car getCar() {
        Car car = new Car();
        car.setBrand("bugatti");
        return car;
    }
}
