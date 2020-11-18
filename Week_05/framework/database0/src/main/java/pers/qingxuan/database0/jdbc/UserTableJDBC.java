package pers.qingxuan.database0.jdbc;

import pers.qingxuan.database0.AbstractUserTableOp;

import java.sql.*;

/**
 * <p> jdbc 实现
 *
 * @author : QingXuan
 * @since Created in 下午10:37 2020/11/17
 */
public class UserTableJDBC extends AbstractUserTableOp {
    private final Connection connection;

    public UserTableJDBC(Connection connection) {
        this.connection = connection;
    }


    /**
     * 提供 Connection 连接
     *
     * @return {@link Connection}
     */
    @Override
    public Connection getConnection() {
        return connection;
    }
}
