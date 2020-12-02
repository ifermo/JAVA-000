package pers.qingxuan.insert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qingxuan.insert.entity.OrderForm;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * <p> 单条顺序插入
 *
 * @author : QingXuan
 * @since Created in 下午7:45 2020/12/2
 */
@Component
public class OrderInsertServiceSingleImpl implements OrderInsertService {
    private final String sql = "INSERT INTO order_form(`order_id`,`user_id`,`addr_id`,`total_price`,`state`) VALUES (?,?,?,?,?)";

    @Autowired
    private DataSource dataSource;

    @Override
    public void insert(List<OrderForm> list) throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (OrderForm order : list) {
            preparedStatement.setLong(1, order.getOrderId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getAddrId());
            preparedStatement.setBigDecimal(4, order.getTotalPrice());
            preparedStatement.setByte(5, order.getState());
            preparedStatement.execute();
        }
    }
}
