package pers.qingxuan.wrsv1.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pers.qingxuan.wrsv1.entity.Product;
import pers.qingxuan.wrsv1.service.ProductService;

import static pers.qingxuan.wrsv1.consts.Constant.PRIMARY_JDBC;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:23 2020/12/1
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final JdbcTemplate jdbcTemplate;

    public ProductServiceImpl(@Qualifier(PRIMARY_JDBC) JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO product(`product_id`,`product_name`,`price`,`quantity`,`picture`,`product_desc`,`shop_id`,`state`) VALUES (?,?,?,?,?,?,?,?)";
        Object[] args = new Object[]{
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getQuantity(),
                product.getPicture(),
                product.getProductDesc(),
                product.getShopId(),
                product.getState()
        };
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET product_name = ?,price=?,quantity=?,picture=?,product_desc=?,shop_id=?,state=? WHERE product_id=?";
        Object[] args = new Object[]{
                product.getProductName(),
                product.getPrice(),
                product.getQuantity(),
                product.getPicture(),
                product.getProductDesc(),
                product.getShopId(),
                product.getState(),
                product.getProductId()
        };
        jdbcTemplate.update(sql, args);
        return false;
    }
}
