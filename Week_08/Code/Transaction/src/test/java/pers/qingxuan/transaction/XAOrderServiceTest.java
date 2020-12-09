package pers.qingxuan.transaction;

import org.apache.shardingsphere.transaction.core.TransactionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import pers.qingxuan.transaction.conf.TransactionConfiguration;
import pers.qingxuan.transaction.entity.Order;
import pers.qingxuan.transaction.service.XAOrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:27 2020/12/9
 */
@SpringBootTest(classes = XAOrderServiceTest.class)
@Import(TransactionConfiguration.class)
public class XAOrderServiceTest {
    @Autowired
    private XAOrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService.init();
    }

    @AfterEach
    public void cleanUp() {
        orderService.cleanup();
    }

    @Test
    public void assertInsertSuccess() {
        assertEquals(orderService.insert(orders(16)), TransactionType.XA);
        assertEquals(orderService.selectAll(), 10);
    }

    @Test
    public void assertInsertFailed() {
        try {
            orderService.insertFailed(orders(16));
        } catch (final Exception ignore) {
        }
        assertEquals(orderService.selectAll(), 0);
    }

    /**
     * create order list
     *
     * @return orderList
     */
    private List<Order> orders(int count) {
        List<Order> orders = new ArrayList<>(count);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setAddrId(count - i);
            order.setTotalPrice(BigDecimal.valueOf(random.nextInt(100)));
            order.setState((byte) 1);
        }
        return orders;
    }
}
