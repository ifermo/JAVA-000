package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;
import com.qingxuan.get.Result;

import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.LockSupport;

/**
 * <p> LockSupport 实现
 *
 * @author : QingXuan
 * @since Created in 8:41 下午 2020/11/11
 */
public class LockSupportImpl {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        Thread mainThread = Thread.currentThread();
        Result result = new Result();
        new Thread(() -> {
            result.setValue(fibonacci.calc(36));
            LockSupport.unpark(mainThread);
        }).start();
        LockSupport.park();
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
