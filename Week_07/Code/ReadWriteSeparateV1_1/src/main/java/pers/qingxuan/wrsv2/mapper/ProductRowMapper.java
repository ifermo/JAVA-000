package pers.qingxuan.wrsv2.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pers.qingxuan.wrsv2.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p> Product RowMapper
 *
 * @author : QingXuan
 * @since Created in 下午8:32 2020/12/1
 */
@Component
public class ProductRowMapper implements RowMapper<Product> {
    /**
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call {@code next()} on
     * the ResultSet; it is only supposed to map values of the current row.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setQuantity(rs.getInt("picture"));
        product.setProductDesc(rs.getString("product_desc"));
        product.setShopId(rs.getInt("shop_id"));
        product.setState(rs.getByte("state"));
        product.setGmtCreate(rs.getTimestamp("gmt_create").toLocalDateTime());
        product.setGmtModified(rs.getTimestamp("gmt_modified").toLocalDateTime());
        return product;
    }
}
