package pers.qingxuan.redis;

import com.google.gson.Gson;
import io.lettuce.core.pubsub.api.reactive.ChannelMessage;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p> Order Reactive Consumer
 *
 * @author : QingXuan
 * @since Created in 下午8:48 2021/1/5
 */
public class OrderReactiveConsumer implements Consumer<ChannelMessage<String, String>> {
    private final List<OrderService> consumers;
    private final Gson gson = new Gson();

    public OrderReactiveConsumer(List<OrderService> consumers) {
        this.consumers = consumers;
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param message the input argument
     */
    @Override
    public void accept(ChannelMessage<String, String> message) {
        Order order = gson.fromJson(message.getMessage(), Order.class);
        consumers.forEach(consumer -> consumer.handle(order));
    }
}
