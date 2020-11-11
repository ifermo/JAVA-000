package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p> 利用线程池&Future实现
 *
 * @author : QingXuan
 * @since Created in 8:50 上午 2020/11/11
 */
public class FutureImpl {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Long> future = executor.submit(() -> fibonacci.calc(36));
        System.out.println("fibonacci(" + 36 + ")=" + future.get());
        executor.shutdown();
    }
}
