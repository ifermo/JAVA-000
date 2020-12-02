package pers.qingxuan.wrsv1.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.qingxuan.wrsv1.entity.Product;

import java.math.BigDecimal;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:55 2020/12/1
 */
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Test
    void addProduct() {
        Product product=new Product();
        product.setProductId(1);
        product.setProductName("自然哲学的数学原理");
        product.setPrice(BigDecimal.valueOf(99));
        product.setQuantity(100);
        product.setPicture("");
        product.setProductDesc("数学+哲学");
        product.setShopId(101);
        product.setState((byte) 1);
        productService.addProduct(product);
    }
}