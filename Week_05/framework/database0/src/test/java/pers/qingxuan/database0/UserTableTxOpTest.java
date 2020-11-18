package pers.qingxuan.database0;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qingxuan.database0.jdbc.UserTableJDBC;
import pers.qingxuan.database0.tx.UserTableTxOp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p> jdbc 事物测试
 *
 * @author : QingXuan
 * @since Created in 下午7:41 2020/11/18
 */
class UserTableTxOpTest {
    public static final Logger log = LoggerFactory.getLogger(UserTableTxOpTest.class);

    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private Connection conn;
    private UserTableTxOp tableTxOp;

    @BeforeEach
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_NAME);
        conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        tableTxOp = new UserTableTxOp(conn);
    }

    @AfterEach
    public void release() {
        tableTxOp = null;
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void insertAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "jack", 18));
        users.add(new User(2, "jetty", 19));
        users.add(new User(3, "netty", 20));
        users.add(new User(4, "jack", 30));
        try {
            tableTxOp.insertAll(users);
            log.info("操作完成！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}