package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;

import java.util.concurrent.CompletableFuture;

/**
 * <p> 利用CompletableFuture实现
 *
 * @author : QingXuan
 * @since Created in 8:59 上午 2020/11/11
 */
public class CompletableFutureImpl {

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> fibonacci.calc(36));
        System.out.println("fibonacci(" + 36 + ")=" + future.join());
    }
}
