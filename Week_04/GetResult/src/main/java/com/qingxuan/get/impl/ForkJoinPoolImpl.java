package com.qingxuan.get.impl;

import com.qingxuan.get.FibonacciTask;

import java.util.concurrent.ForkJoinPool;

/**
 * <p> 通过ForkJoinPool实现
 *
 * @author : QingXuan
 * @since Created in 9:01 上午 2020/11/11
 */
public class ForkJoinPoolImpl {

    public static void main(String[] args) {
        //创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        //创建分治任务
        FibonacciTask fib = new FibonacciTask(36);
        //启动分治任务
        Long result = fjp.invoke(fib);
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
