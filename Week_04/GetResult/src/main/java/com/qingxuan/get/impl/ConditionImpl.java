package com.qingxuan.get.impl;

import com.qingxuan.get.Fibonacci;
import com.qingxuan.get.Result;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> 利用Lock.Condition 实现
 *
 * @author : QingXuan
 * @since Created in 8:59 上午 2020/11/11
 */
public class ConditionImpl {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Fibonacci fibonacci = new Fibonacci();
        Result result = new Result();

        new Thread(() -> {
            try {
                Thread.sleep(1);
                lock.lock();
                result.setValue(fibonacci.calc(36));
                // condition.signal();
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        try {

            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
