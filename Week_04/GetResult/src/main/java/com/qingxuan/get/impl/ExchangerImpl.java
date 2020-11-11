package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;

import java.util.concurrent.Exchanger;

/**
 * <p> Exchanger 实现
 *
 * @author : QingXuan
 * @since Created in 8:25 下午 2020/11/11
 */
public class ExchangerImpl {
    public static void main(String[] args) throws InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        Exchanger<Long> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                exchanger.exchange(fibonacci.calc(36));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Long result = exchanger.exchange(0L);
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
