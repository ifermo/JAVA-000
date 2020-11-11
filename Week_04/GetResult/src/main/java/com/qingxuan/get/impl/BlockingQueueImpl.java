package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * <p> 使用阻塞队列的方式实现
 *
 * @author : QingXuan
 * @since Created in 8:54 上午 2020/11/11
 */
public class BlockingQueueImpl {

    public static void main(String[] args) throws InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        BlockingQueue<Long> queue = new SynchronousQueue<>();
        // BlockingQueue<Long> queue = new ArrayBlockingQueue<>(2);
        // BlockingQueue<Long> queue = new LinkedBlockingQueue<>();
        // BlockingQueue<Long> queue = new PriorityBlockingQueue<>();
        // BlockingQueue<Long> queue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                queue.put(fibonacci.calc(36));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Long result = queue.take();
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
