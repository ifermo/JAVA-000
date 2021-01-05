package pers.qingxuan.redis;

import com.google.gson.Gson;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.reactive.ChannelMessage;
import io.lettuce.core.pubsub.api.reactive.RedisPubSubReactiveCommands;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * <p> 发布订阅 Reactive API test
 *
 * @author : QingXuan
 * @since Created in 下午8:26 2021/1/5
 */
public class ReactivePubSubTester {
    private StatefulRedisPubSubConnection<String, String> subConnection;
    private StatefulRedisPubSubConnection<String, String> pubConnection;
    private RedisClient client;
    private final Gson gson = new Gson();

    private final String orderChannel = "orders";

    @BeforeEach
    void setUp() {
        RedisURI redisURI = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withDatabase(0)
                .withTimeout(Duration.ofMinutes(10))
                .build();
        client = RedisClient.create(redisURI);
        subConnection = client.connectPubSub();
        pubConnection = client.connectPubSub();

        // 订阅
        List<OrderService> orderServices = Collections.singletonList(new OrderServiceImpl());

        RedisPubSubReactiveCommands<String, String> reactive = subConnection.reactive();
        reactive.subscribe(orderChannel).subscribe();
        reactive.observeChannels().doOnNext(new OrderReactiveConsumer(orderServices)).subscribe();

    }

    @AfterEach
    void tearDown() {
        pubConnection.close();
        subConnection.close();
        client.shutdown();
    }

    @Test
    public void test() throws InterruptedException {
        OrderFactory factory=new OrderFactory();

        RedisPubSubReactiveCommands<String, String> pubCommands = pubConnection.reactive();
        for (int i = 0; i < 10; i++) {
            pubCommands.publish(orderChannel, gson.toJson(factory.newOrder())).subscribe();
        }
        TimeUnit.SECONDS.sleep(5);
    }

}
