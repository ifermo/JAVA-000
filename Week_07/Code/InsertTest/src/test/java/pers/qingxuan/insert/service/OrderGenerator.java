package pers.qingxuan.insert.service;

import pers.qingxuan.insert.entity.OrderForm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 创建订单
 *
 * @author : QingXuan
 * @since Created in 下午8:25 2020/12/2
 */
public class OrderGenerator {
    public static final Random random = new Random();

    public static List<OrderForm> getOrder(int k) {
        List<OrderForm> list = new ArrayList<>(k);
        AtomicLong orderId = new AtomicLong(System.currentTimeMillis());
        for (int i = 0; i < k; i++) {
            OrderForm order = new OrderForm();
            order.setOrderId(orderId.incrementAndGet());
            order.setUserId(random.nextInt());
            order.setAddrId(random.nextInt());
            order.setTotalPrice(BigDecimal.valueOf(random.nextInt()));
            order.setState((byte) 1);
            list.add(order);
        }
        return list;
    }
}
