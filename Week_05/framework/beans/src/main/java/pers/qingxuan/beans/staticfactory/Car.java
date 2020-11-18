package pers.qingxuan.beans.staticfactory;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午11:02 2020/11/16
 */
public class Car {
    private String brand;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
