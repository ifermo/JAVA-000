package pers.qingxuan.redis;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:54 2021/1/5
 */
public class OrderFactory {

    private final AtomicInteger idCounter = new AtomicInteger(1);
    private final Random random = new Random();

    public Order newOrder() {
        Order order = new Order();
        order.setOrderId(idCounter.getAndIncrement());
        order.setUserId(random.nextInt(10000));
        order.setShopId(random.nextInt(1000));
        order.setAmount(String.valueOf(random.nextInt(200)));
        order.setOrderTime(LocalDateTime.now());
        return order;
    }
}
