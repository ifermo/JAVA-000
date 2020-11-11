package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;

import java.util.concurrent.*;

/**
 * <p> 利用CompletionService实现
 *
 * @author : QingXuan
 * @since Created in 9:00 上午 2020/11/11
 */
public class CompletionServiceImpl {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletionService<Long> compService = new ExecutorCompletionService<>(executor);

        Fibonacci fibonacci = new Fibonacci();

        compService.submit(() -> fibonacci.calc(36));

        Long result=compService.take().get();

        System.out.println("fibonacci(" + 36 + ")=" + result);
        executor.shutdown();
    }
}
