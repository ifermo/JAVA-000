package pers.qingxuan.redis.counter;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <p> redis 分布式计数器实现 测试
 *
 * @author : QingXuan
 * @since Created in 下午7:06 2021/1/5
 */
class RedisCounterTest {

    public static final Logger log = LoggerFactory.getLogger(RedisCounterTest.class);

    private StatefulRedisConnection<String, String> connection;
    private RedisClient client;
    private RedisCounter counter;

    @BeforeEach
    void setUp() {
        RedisURI redisURI = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withDatabase(0)
                .withTimeout(Duration.ofMinutes(10))
                .build();
        client = RedisClient.create(redisURI);
        connection = client.connect();
        counter = new RedisCounter(connection.sync(), "amount");
    }

    @AfterEach
    void tearDown() {
        connection.close();
        client.shutdown();
    }

    @Test
    void get() {
        log.info("amount:{}", counter.get());
    }

    @Test
    void increment() {
        long expected = counter.get() + 1;
        assertEquals(expected, counter.increment());
    }

    @Test
    void testIncrement() {
        long expected = counter.get() + 3;
        assertEquals(expected, counter.increment(3));
    }

    @Test
    void decrement() {
        long expected = counter.get() - 1;
        assertEquals(expected, counter.decrement());
    }

    @Test
    void testDecrement() {
        long expected = counter.get() - 5;
        assertEquals(expected, counter.decrement(5));
    }

    @Test
    void compareAndSet() {
        long expected = counter.get();
        assertTrue(counter.compareAndSet(expected, 7));
    }
}