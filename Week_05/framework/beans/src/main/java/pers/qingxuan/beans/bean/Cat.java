package pers.qingxuan.beans.bean;

import org.springframework.stereotype.Component;

/**
 * <p> Cat
 *
 * @author : QingXuan
 * @since Created in 下午10:29 2020/11/16
 */
@Component
public class Cat {
    private String name;
    private String color;

    @Override
    public String toString() {
        return "Cat{" +
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
