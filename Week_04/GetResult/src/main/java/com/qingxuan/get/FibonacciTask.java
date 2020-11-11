package com.qingxuan.get;

import java.util.concurrent.RecursiveTask;

/**
 * <p> ForkJoinPool Task
 *
 * @author : QingXuan
 * @since Created in 3:45 下午 2020/11/10
 */
public class FibonacciTask extends RecursiveTask<Long> {

    private final int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    protected Long compute() {
        if (n <= 1) {
            return (long)n;
        }
        FibonacciTask f1 = new FibonacciTask(n - 1);
        //创建子任务
        f1.fork();
        FibonacciTask f2 = new FibonacciTask(n - 2);
        //等待子任务结果，并合并结果
        return f2.compute() + f1.join();
    }
}