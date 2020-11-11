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
        new Thread(() -> {
            try {
                semaphore.acquire();
                result.setValue(fibonacci.calc(36));
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 休眠1ms，让子线程先得到信号量
        Thread.sleep(1);
        semaphore.acquire();
        semaphore.release();
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
