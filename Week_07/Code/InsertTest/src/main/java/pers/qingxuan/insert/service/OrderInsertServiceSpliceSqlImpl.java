package pers.qingxuan.insert.service;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qingxuan.insert.entity.OrderForm;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p> 拼SQL，即insert values (),(),()的形式
 *
 * @author : QingXuan
 * @since Created in 下午7:45 2020/12/2
 */
@Component
public class OrderInsertServiceSpliceSqlImpl implements OrderInsertService {

    @Autowired
    private DataSource dataSource;

    private final String sql = "INSERT INTO order_form(`order_id`,`user_id`,`addr_id`,`total_price`,`state`) VALUES";

    private final String prefix = "(";
    private final String suffix = ")";
    private final String comma = ",";

    @Override
    public void insert(List<OrderForm> list) throws Exception {
        Connection connection = dataSource.getConnection();

        // valueSql=(?,?,?,?,?),(?,?,?,?,?),···
        String valueSql = list.stream()
                .map(this::toSqlValue)
                .collect(Collectors.joining(comma));

        // 拼接SQL
        String sql0 = sql + valueSql;
        PreparedStatement preparedStatement = connection.prepareStatement(sql0);
        preparedStatement.execute();
    }

    /**
     * 构造形如 (?,?,?,?,?) 的字符串
     *
     * @param order {@link OrderForm}
     * @return valueSql
     */
    private String toSqlValue(OrderForm order) {
        String vl = Joiner.on(comma).join(
                order.getOrderId(),
                order.getUserId(),
                order.getAddrId(),
                order.getTotalPrice(),
                order.getState()
        );
        return prefix + vl + suffix;
    }
}
