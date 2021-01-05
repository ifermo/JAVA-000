package pers.qingxuan.redis.locks;

import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.sync.RedisCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> redis 实现分布式锁
 *
 * @author : QingXuan
 * @since Created in 下午7:48 2021/1/4
 */
public class DefaultRedisLock implements RedisLock {
    public static final Logger log = LoggerFactory.getLogger(DefaultRedisLock.class);

    // 释放锁 Lua 脚本
    private static final String RELEASE_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    // 锁超时重设 Lua 脚本
    public static final String LAST_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1], ARGV[2]) else return 0 end";
    // 锁超时时间
    private final long timeout;
    // 锁续期线程
    private final Thread keepThread;
    // 锁延期超时标识
    private volatile boolean keep = true;
    // 锁持有线程
    private final Thread ownerThread = Thread.currentThread();

    // 锁计数器
    final AtomicInteger lockCount = new AtomicInteger(1);

    private final RedisCommands<String, String> redisCommands;
    private final String lockKey;
    private final String lockValue;
    private final KeyCleaner cleaner;

    public DefaultRedisLock(RedisCommands<String, String> redisCommands, String lockKey, String lockValue,
                            long timeout, KeyCleaner cleaner) {
        this.redisCommands = redisCommands;
        this.lockKey = lockKey;
        this.lockValue = lockValue;
        this.timeout = timeout;
        this.cleaner = cleaner;
        this.keepThread = startKeeper();
    }

    @Override
    public void release() {
        if (!isMyLock()) {
            throw new IllegalMonitorStateException("You do not own the lock");
        }
        int lockCount = this.lockCount.decrementAndGet();
        if (lockCount > 0) {
            log.info("---relocked release---");
        } else if (lockCount < 0) {
            throw new IllegalMonitorStateException("Lock count has gone negative for lock");
        }
        // 当 lockCount 等于0时，代表所有重入情况释放完毕，此时可以结束续费线程，并释放锁
        try {
            // 停止续期线程
            stop();
            keepThread.interrupt();
            // 释放锁
            redisCommands.eval(RELEASE_SCRIPT, ScriptOutputType.INTEGER, new String[]{lockKey}, lockValue);
            log.debug("---locked release---");
        } finally {
            cleaner.remove(lockKey);
        }
    }

    @Override
    public boolean isMyLock() {
        return ownerThread == Thread.currentThread();
    }


    /**
     * 启动锁超时延期线程
     *
     * @return 续期线程
     */
    private Thread startKeeper() {
        Thread thread = new Thread(new LockKeepRunnable());
        thread.setDaemon(true);
        thread.start();
        return thread;
    }

    /**
     * 设置停止续期标识
     */
    private void stop() {
        this.keep = false;
    }


    /**
     * 锁延期超时
     */
    private class LockKeepRunnable implements Runnable {

        @Override
        public void run() {
            while (keep) {
                try {
                    // 休眠一下，防止过频繁
                    TimeUnit.MILLISECONDS.sleep((timeout * 1000) >> 1);

                    String[] keys = new String[]{lockKey};
                    String[] values = new String[]{lockValue, String.valueOf(timeout)};
                    // 这里采用 lua 脚本 “续费锁” ，注意要续费是自己持有的锁， value 值唯一确认现在这把锁是自己持有的
                    Long result = redisCommands.eval(LAST_SCRIPT, ScriptOutputType.INTEGER, keys, values);
                    if (result == 1) {
                        log.info("The extension is successful, reset the lock timeout to {}s", timeout);
                    } else {
                        log.warn("Extension failed");
                        stop();
                    }
                } catch (InterruptedException ignored) {
                } catch (Exception e) {
                    log.warn("Deferred thread exception");
                    e.printStackTrace();
                }
            }
            log.debug("End of postponement");
        }
    }
}
