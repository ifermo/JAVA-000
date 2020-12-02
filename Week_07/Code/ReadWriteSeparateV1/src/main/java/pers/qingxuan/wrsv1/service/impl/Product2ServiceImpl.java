package pers.qingxuan.wrsv1.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pers.qingxuan.wrsv1.entity.Product;
import pers.qingxuan.wrsv1.mapper.ProductRowMapper;
import pers.qingxuan.wrsv1.service.Product2Service;

import java.util.List;

import static pers.qingxuan.wrsv1.consts.Constant.RETINUE_JDBC;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:23 2020/12/1
 */
@Service
public class Product2ServiceImpl implements Product2Service {
    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper rowMapper;

    public Product2ServiceImpl(@Qualifier(RETINUE_JDBC) JdbcTemplate jdbcTemplate,
                               ProductRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public Product findById(long productId) {
        String sql = "SELECT * FROM product WHERE product_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{productId}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> findByName(String name) {
        String sql = "SELECT * FROM product WHERE product_name LIKE ?";
        Object[] args = new Object[]{name + "%"};
        return jdbcTemplate.query(sql, args, rowMapper);
    }
}
