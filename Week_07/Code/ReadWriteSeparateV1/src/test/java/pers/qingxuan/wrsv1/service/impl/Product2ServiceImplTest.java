package pers.qingxuan.wrsv1.service.impl;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.qingxuan.wrsv1.entity.Product;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:55 2020/12/1
 */
@SpringBootTest
class Product2ServiceImplTest {
    private final Logger log= LoggerFactory.getLogger(Product2ServiceImplTest.class);

    @Autowired
    Product2ServiceImpl product2Service;

    @Test
    void findById() {
        Product product=product2Service.findById(1);
        log.info(product.toString());
    }
}