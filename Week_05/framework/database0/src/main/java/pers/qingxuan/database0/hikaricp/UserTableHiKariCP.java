package pers.qingxuan.database0.hikaricp;

import com.zaxxer.hikari.HikariDataSource;
import pers.qingxuan.database0.AbstractUserTableOp;
import pers.qingxuan.database0.User;
import pers.qingxuan.database0.UserTableOp;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * <p> 基于 HiKariCP 实现
 *
 * @author : QingXuan
 * @since Created in 下午6:52 2020/11/18
 */
public class UserTableHiKariCP implements UserTableOp {
    private final DataSource dataSource;
    private final UserTableOperator operator;

    public UserTableHiKariCP(DataSource dataSource) {
        this.dataSource = dataSource;
        this.operator = new UserTableOperator();
    }

    @Override
    public List<User> queryByName(String username) throws SQLException {
        List<User> result;
        try (Connection conn = dataSource.getConnection()) {
            operator.setConnection(conn);
            result = operator.queryByName(username);
        } finally {
            operator.setConnection(null);
        }
        return result;
    }

    @Override
    public boolean insert(User user) throws SQLException {
        boolean result;
        try (Connection conn = dataSource.getConnection()) {
            operator.setConnection(conn);
            result = operator.insert(user);
        } finally {
            operator.setConnection(null);
        }
        return result;
    }

    @Override
    public boolean deleteById(int uid) throws SQLException {
        boolean result;
        try (Connection conn = dataSource.getConnection()) {
            operator.setConnection(conn);
            result = operator.deleteById(uid);
        } finally {
            operator.setConnection(null);
        }
        return result;
    }

    @Override
    public boolean updateById(User user) throws SQLException {
        boolean result;
        try (Connection conn = dataSource.getConnection()) {
            operator.setConnection(conn);
            result = operator.updateById(user);
        } finally {
            operator.setConnection(null);
        }
        return result;
    }

    private static class UserTableOperator extends AbstractUserTableOp {
        private Connection connection;

        public void setConnection(Connection connection) {
            this.connection = connection;
        }

        /**
         * 提供 Connection 连接
         *
         * @return {@link Connection}
         */
        @Override
        public Connection getConnection() throws SQLException {
            return connection;
        }
    }
}
