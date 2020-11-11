package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;
import com.qingxuan.get.Result;

import java.util.concurrent.CountDownLatch;

/**
 * <p> 利用CountDownLatch 实现
 *
 * @author : QingXuan
 * @since Created in 8:48 上午 2020/11/11
 */
public class CountDownLatchImpl {
    
    public static void main(String[] args) throws InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        Result result = new Result();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            result.setValue(fibonacci.calc0(51));
            latch.countDown();
        }).start();
        latch.await();
        System.out.println("fibonacci(" + 51 + ")=" + result);
    }
}
