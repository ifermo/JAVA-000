package pers.qingxuan.insert.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.qingxuan.insert.entity.OrderForm;

import javax.annotation.PostConstruct;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:20 2020/12/2
 */
@SpringBootTest
class OrderInsertServiceSingleImplTest {
    public static final Logger log = LoggerFactory.getLogger(OrderInsertServiceSingleImplTest.class);

    @Autowired
    OrderInsertServiceSingleImpl orderInsertService;

    /**
     * 5628200ms
     * 6124100ms
     * @throws Exception
     */
    @Test
    void insert() throws Exception {
        List<OrderForm> list = OrderGenerator.getOrder(1_000_000);
        long startTimestamp = System.currentTimeMillis();
        orderInsertService.insert(list);
        long diff = System.currentTimeMillis() - startTimestamp;
        log.info("顺序插入方式耗时 {} ms", diff);
    }
}