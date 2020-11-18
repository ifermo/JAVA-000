package pers.qingxuan.beans.instancefactory;

/**
 * <p> 树立工厂
 *
 * @author : QingXuan
 * @since Created in 下午11:12 2020/11/16
 */
public class FlowerInstanceFactory {

    public Flower flower() {
        Flower flower = new Flower();
        flower.setName("rose");
        flower.setColor("蓝色");
        return flower;
    }
}
