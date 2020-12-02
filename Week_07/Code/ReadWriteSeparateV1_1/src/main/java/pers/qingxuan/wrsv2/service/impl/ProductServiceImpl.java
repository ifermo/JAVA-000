package pers.qingxuan.wrsv2.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pers.qingxuan.wrsv2.aop.DataSourceWith;
import pers.qingxuan.wrsv2.entity.Product;
import pers.qingxuan.wrsv2.mapper.ProductRowMapper;
import pers.qingxuan.wrsv2.service.ProductService;

import javax.sql.DataSource;
import java.util.List;

import static pers.qingxuan.wrsv2.consts.Constant.PRIMARY_SOURCE;
import static pers.qingxuan.wrsv2.consts.Constant.RETINUE_SOURCE;


/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:23 2020/12/1
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper rowMapper;

    public ProductServiceImpl(DataSource dataSource, ProductRowMapper rowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
    }

    @Override
    @DataSourceWith(PRIMARY_SOURCE)
    public Product findById(long productId) {
        String sql = "SELECT * FROM product WHERE product_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{productId}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @DataSourceWith(RETINUE_SOURCE)
    public List<Product> findByName(String name) {
        String sql = "SELECT * FROM product WHERE product_name LIKE ?";
        Object[] args = new Object[]{name + "%"};
        return jdbcTemplate.query(sql, args, rowMapper);
    }
}
