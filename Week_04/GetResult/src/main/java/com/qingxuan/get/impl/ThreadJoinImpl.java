package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;
import com.qingxuan.get.Result;

/**
 * <p> 使用Thread#join方法
 *
 * @author : QingXuan
 * @since Created in 8:51 上午 2020/11/11
 */
public class ThreadJoinImpl {

    public static void main(String[] args) throws InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        Result result = new Result();
        Thread worker = new Thread(() -> result.setValue(fibonacci.calc(36)));
        worker.start();
        worker.join();
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
