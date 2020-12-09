package pers.qingxuan.transaction.service;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qingxuan.transaction.entity.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p> XA 事务操作
 *
 * @author : QingXuan
 * @since Created in 下午10:16 2020/12/9
 */
@Service
public class XAOrderService {
    private final JdbcTemplate jdbcTemplate;

    public static final String INSET_SQL = "INSERT INTO t_order (user_id, addr_id,total_price,state) VALUES (?, ?, ?,?)";

    @Autowired
    public XAOrderService(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Init.
     */
    public void init() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS t_order");
        jdbcTemplate.execute("create table t_order\n" +
                "(\n" +
                "    order_id bigint default 0 not null comment '订单ID',\n" +
                "    user_id int not null comment '用户ID',\n" +
                "    addr_id int null comment '收件地址ID',\n" +
                "    total_price decimal default 0 null comment '订单价格',\n" +
                "    state tinyint null comment '订单状态',\n" +
                "    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',\n" +
                "    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'\n" +
                ")");
    }

    /**
     * Clean up.
     */
    public void cleanup() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS t_order");
    }

    /**
     * Execute XA.
     *
     * @param list order list
     * @return transaction type
     */
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public TransactionType insert(List<Order> list) {
        return jdbcTemplate.execute(INSET_SQL, (PreparedStatementCallback<TransactionType>) preparedStatement -> {
            doInsert(list, preparedStatement);
            return TransactionTypeHolder.get();
        });
    }

    /**
     * Execute XA with exception.
     *
     * @param list order list
     */
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insertFailed(List<Order> list) {
        jdbcTemplate.execute(INSET_SQL, (PreparedStatementCallback<TransactionType>) preparedStatement -> {
            doInsert(list, preparedStatement);
            throw new SQLException("mock transaction failed");
        });
    }

    private void doInsert(List<Order> list, final PreparedStatement preparedStatement) throws SQLException {
        for (Order order : list) {
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getAddrId());
            preparedStatement.setBigDecimal(3,order.getTotalPrice());
            preparedStatement.setByte(4,order.getState());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Select all.
     *
     * @return record count
     */
    public int selectAll() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) AS count FROM t_order", Integer.class);
    }
}
