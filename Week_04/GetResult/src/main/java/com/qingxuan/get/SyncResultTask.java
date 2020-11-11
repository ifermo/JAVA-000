package com.qingxuan.get;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 1:58 下午 2020/11/10
 */
public class SyncResultTask implements Runnable {
    private Fibonacci fibonacci = new Fibonacci();
    private Result result = new Result();

    @Override
    public void run() {
        synchronized (this) {
            result.setValue(fibonacci.calc(36));
            notifyAll();
        }
    }

    public synchronized Result getResult() throws InterruptedException {
        wait();
        return result;
    }
}
