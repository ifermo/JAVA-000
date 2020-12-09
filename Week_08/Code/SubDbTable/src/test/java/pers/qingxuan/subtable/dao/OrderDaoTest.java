package pers.qingxuan.subtable.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.qingxuan.subtable.entity.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:37 2020/12/9
 */
@SpringBootTest
class OrderDaoTest {
    public static final Logger log = LoggerFactory.getLogger(OrderDaoTest.class);

    @Autowired
    OrderDao orderDao;

    @Test
    void insert() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Order order = new Order();
                order.setUserId(i);
                order.setAddrId(j);
                order.setTotalPrice(BigDecimal.valueOf(random.nextInt(100)));
                order.setState((byte) 1);
                orderDao.insertSelective(order);
            }
        }
    }

    @Test
    void select() {
        List<Order> list = orderDao.selectByUserId(20, 2);
        list.forEach(order->{
            log.info(order.toString());
        });

        List<Order> list2 = orderDao.selectByUserId(33, 2);
        list2.forEach(order->{
            log.info(order.toString());
        });
    }
}