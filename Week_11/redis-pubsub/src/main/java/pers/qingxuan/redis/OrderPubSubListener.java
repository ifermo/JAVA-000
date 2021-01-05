package pers.qingxuan.redis;

import com.google.gson.Gson;
import io.lettuce.core.pubsub.RedisPubSubAdapter;

import java.util.List;

/**
 * <p> 工单订阅
 *
 * @author : QingXuan
 * @since Created in 下午8:12 2021/1/5
 */
public class OrderPubSubListener extends RedisPubSubAdapter<String, String> {
    private final List<OrderService> consumers;
    private final Gson gson = new Gson();

    public OrderPubSubListener(List<OrderService> consumers) {
        this.consumers = consumers;
    }

    @Override
    public void message(String channel, String message) {
        Order order = gson.fromJson(message, Order.class);
        consumers.forEach(consumer -> consumer.handle(order));
    }
}
