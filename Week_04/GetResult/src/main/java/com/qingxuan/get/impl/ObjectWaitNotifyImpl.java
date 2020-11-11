package com.qingxuan.get.impl;

import com.qingxuan.get.Result;
import com.qingxuan.get.SyncResultTask;

/**
 * <p> 利用Object#wait配合Object#notify（Object#notifyAll）实现
 *
 * @author : QingXuan
 * @since Created in 8:53 上午 2020/11/11
 */
public class ObjectWaitNotifyImpl {

    public static void main(String[] args) throws InterruptedException {
        SyncResultTask task = new SyncResultTask();
        new Thread(task).start();
        Result result = task.getResult();
        System.out.println("fibonacci(" + 36 + ")=" + result);
    }
}
