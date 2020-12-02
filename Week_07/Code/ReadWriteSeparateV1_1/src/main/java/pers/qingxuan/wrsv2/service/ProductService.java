package pers.qingxuan.wrsv2.service;

import pers.qingxuan.wrsv2.entity.Product;

import java.util.List;

/**
 * <p> 商品服务
 *
 * @author : QingXuan
 * @since Created in 下午8:15 2020/12/1
 */
public interface ProductService {
    Product findById(long productId);

    List<Product> findByName(String productName);
}
