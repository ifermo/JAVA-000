package pers.qingxuan.redis.locks;

/**
 * <p> redis lock
 *
 * @author : QingXuan
 * @since Created in 下午10:35 2021/1/4
 */
public interface RedisLock {
    /**
     * 释放锁
     */
    void release();

    /**
     * 校验所持有线程是否是调用线程
     *
     * @return true/false
     */
    boolean isMyLock();
}
