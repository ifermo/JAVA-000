package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;
import com.qingxuan.get.Result;

import java.util.concurrent.TimeUnit;

/**
 * <p> 利用线程休眠实现
 *
 * @author : QingXuan
 * @since Created in 8:44 上午 2020/11/11
 */
public class ThreadSleepImpl {

    public static void main(String[] args) throws InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        Result result = new Result();
        new Thread(() -> {
            result.setValue(fibonacci.calc(36));
        }).start();
        // 前提是自线程能在指定时间内执行完成
        TimeUnit.SECONDS.sleep(1);
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }

}
