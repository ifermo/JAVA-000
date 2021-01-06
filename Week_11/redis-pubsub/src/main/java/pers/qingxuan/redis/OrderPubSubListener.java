package pers.qingxuan.redis;

import io.lettuce.core.pubsub.RedisPubSubAdapter;
import pers.qingxuan.redis.json.GsonJsonSupport;
import pers.qingxuan.redis.json.JsonSupport;

import java.util.List;

/**
 * <p> 工单消息回调
 *
 * @author : QingXuan
 * @since Created in 下午8:12 2021/1/5
 */
public class OrderPubSubListener extends RedisPubSubAdapter<String, String> {
    private final List<OrderService> consumers;
    private final JsonSupport jsonSupport = new GsonJsonSupport();

    public OrderPubSubListener(List<OrderService> consumers) {
        this.consumers = consumers;
    }

    @Override
    public void message(String channel, String message) {
        Order order = jsonSupport.fromJson(message, Order.class);
        consumers.forEach(consumer -> consumer.handle(order));
    }
}
