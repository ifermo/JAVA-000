package pers.qingxuan.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qingxuan.redis.locks.LockManager;
import pers.qingxuan.redis.locks.RedisLock;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <p> redis 实现分布式锁 测试
 *
 * @author : QingXuan
 * @since Created in 下午9:25 2021/1/4
 */
public class RedisLockTester {
    public static final Logger log = LoggerFactory.getLogger(RedisLockTester.class);

    private StatefulRedisConnection<String, String> connection;
    private RedisClient client;

    private LockManager lockManager;

    @Test
    public void test() throws InterruptedException {
        final String lockKey = "lock";
        Runnable runnable = () -> {
            Optional<RedisLock> lock = lockManager.tryLock(lockKey, 2);
            if (lock.isEmpty()) {
                log.warn("获取锁 {} 失败", lockKey);
                return;
            }
            try {
                Thread.sleep(2500);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.get().release();
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        TimeUnit.SECONDS.sleep(5);
        new Thread(runnable).start();
        TimeUnit.SECONDS.sleep(5);
    }

    @BeforeEach
    public void before() {
        RedisURI redisURI = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withDatabase(0)
                .withTimeout(Duration.ofMinutes(10))
                .build();
        client = RedisClient.create(redisURI);
        connection = client.connect();
        lockManager = new LockManager(connection.sync());
    }

    @AfterEach
    public void after() {
        connection.close();
        client.shutdown();
    }
}
