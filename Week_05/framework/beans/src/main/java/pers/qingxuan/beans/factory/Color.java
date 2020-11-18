package pers.qingxuan.beans.factory;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:50 2020/11/16
 */
public class Color {
    private int value = 0xFFFFFF;

    public Color() {
    }

    public Color(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Color{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
