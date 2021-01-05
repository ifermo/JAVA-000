package pers.qingxuan.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> OrderService default implement
 *
 * @author : QingXuan
 * @since Created in 下午8:17 2021/1/5
 */
public class OrderServiceImpl implements OrderService {
    public static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public void handle(Order order) {
        log.info("处理了工单 ID:{} detail:{}", order.getOrderId(), order);
    }
}
