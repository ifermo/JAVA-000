package com.qingxuan.get;

/**
 * <p> 斐波拉契数列
 *
 * @author : QingXuan
 * @since Created in 12:45 下午 2020/11/10
 */
public class Fibonacci {

    /**
     * 递归实现
     */
    public long calc(int a) {
        if (a < 2) {
            return a;
        }
        return calc(a - 1) + calc(a - 2);
    }

    /**
     * 循环迭代实现
     */
    public long calc0(int k) {
        if (k < 2) {
            return k;
        }
        long m = 0, n = 1;
        for (int i = 2; i < k; i++) {
            n = m + (m = n);
        }
        return m + n;
    }
}
