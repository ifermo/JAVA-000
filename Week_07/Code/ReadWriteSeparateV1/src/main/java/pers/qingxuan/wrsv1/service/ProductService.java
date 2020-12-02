package pers.qingxuan.wrsv1.service;

import pers.qingxuan.wrsv1.entity.Product;

/**
 * <p> 商品服务
 *
 * @author : QingXuan
 * @since Created in 下午8:15 2020/12/1
 */
public interface ProductService {
    boolean addProduct(Product product);

    boolean updateProduct(Product product);
}
