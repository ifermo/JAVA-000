package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;
import com.qingxuan.get.Result;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p> 利用 CyclicBarrier 实现
 *
 * @author : QingXuan
 * @since Created in 8:49 上午 2020/11/11
 */
public class CyclicBarrierImpl {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        Result result = new Result();
        CyclicBarrier barrier = new CyclicBarrier(2);
        new Thread(() -> {
            result.setValue(fibonacci.calc0(51));
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        barrier.await();
        System.out.println("fibonacci(" + 51 + ")=" + result);
    }
}
