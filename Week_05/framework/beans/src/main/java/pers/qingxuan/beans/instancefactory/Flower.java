package pers.qingxuan.beans.instancefactory;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午11:11 2020/11/16
 */
public class Flower {
    private String name;
    private String color;

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
