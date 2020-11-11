package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;
import com.qingxuan.get.Result;

/**
 * <p> 通过Volatile保证可见性，循环等待的方式实现
 *
 * @author : QingXuan
 * @since Created in 8:57 上午 2020/11/11
 */
public class VolatileAndCycleImpl {

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        Result result = new Result();
        Thread worker = new Thread(() -> result.setValue(fibonacci.calc(36)));
        worker.start();
        while (result.getValue() == 0) {
        }
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
