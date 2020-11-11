package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;
import com.qingxuan.get.Result;

import java.util.concurrent.Semaphore;

/**
 * <p> 利用Semaphore 实现
 *
 * @author : QingXuan
 * @since Created in 8:50 上午 2020/11/11
 */
public class SemaphoreImpl {
    public static void main(String[] args) throws InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        Result result = new Result();
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        new Thread(() -> {
            result.setValue(fibonacci.calc(36));
            semaphore.release();
        }).start();
        semaphore.acquire();
        semaphore.release();
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
