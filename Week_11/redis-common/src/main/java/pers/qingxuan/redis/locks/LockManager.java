package pers.qingxuan.redis.locks;

import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> 锁管理者
 *
 * @author : QingXuan
 * @since Created in 下午9:47 2021/1/4
 */
public class LockManager {
    public static final Logger log = LoggerFactory.getLogger(DefaultRedisLock.class);
    /**
     * 默认超时时间
     */
    private static final long DEFAULT_TIMEOUT = 3;

    private static final String SUCCESS = "OK";

    private final RedisCommands<String, String> redisCommands;

    // 锁缓存
    private final Map<String, DefaultRedisLock> locks;

    public LockManager(RedisCommands<String, String> redisCommands) {
        this.redisCommands = redisCommands;
        this.locks = new ConcurrentHashMap<>();
    }

    public Optional<RedisLock> tryLock(@Nonnull String key) {
        return tryLock(key, DEFAULT_TIMEOUT);
    }

    public Optional<RedisLock> tryLock(@Nonnull String key, long timeout) {
        DefaultRedisLock lock0 = locks.get(key);
        // 判断当前线程是否已经持有锁
        if (lock0 != null && lock0.isMyLock()) {
            log.info("Lock {} is reentered", key);
            lock0.lockCount.incrementAndGet();
            return Optional.of(lock0);
        }

        String value = UUID.randomUUID().toString();
        Optional<RedisLock> result = Optional.empty();

        if (SUCCESS.equals(redisCommands.set(key, value, SetArgs.Builder.nx().ex(timeout)))) {
            DefaultRedisLock lock = new DefaultRedisLock(redisCommands, key, value, timeout, locks::remove);
            locks.put(key, lock);
            result = Optional.of(lock);
        }
        return result;
    }

}
