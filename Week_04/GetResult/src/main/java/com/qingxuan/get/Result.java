package com.qingxuan.get;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 12:50 下午 2020/11/10
 */
public class Result {
    private volatile long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
