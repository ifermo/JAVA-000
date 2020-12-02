package pers.qingxuan.wrsv2.service.impl;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.qingxuan.wrsv2.entity.Product;

import java.util.List;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:03 2020/12/1
 */
@SpringBootTest
class ProductServiceImplTest {
    public static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductServiceImpl productService;

    @Test
    void findById() {
        Product product=productService.findById(1);
        log.info(product.toString());
    }

    @Test
    void findByName() {
        List<Product> list=productService.findByName("自然哲学");
        list.forEach(pt->log.info(pt.toString()));
    }
}