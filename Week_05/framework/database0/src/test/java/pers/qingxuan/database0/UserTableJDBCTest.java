package pers.qingxuan.database0;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qingxuan.database0.jdbc.UserTableJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> jdbc 操作测试类
 *
 * @author : QingXuan
 * @since Created in 下午11:05 2020/11/17
 */
public class UserTableJDBCTest {
    public static final Logger log = LoggerFactory.getLogger(UserTableJDBCTest.class);

    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private Connection conn;
    private UserTableJDBC tableOpJDBC;

    @BeforeEach
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_NAME);
        conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        tableOpJDBC = new UserTableJDBC(conn);
    }

    @AfterEach
    public void release() {
        tableOpJDBC = null;
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testInsert() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "jack", 18));
        users.add(new User(2, "jetty", 19));
        users.add(new User(3, "netty", 20));
        users.add(new User(4, "jack", 30));
        try {
            for (User user : users) {
                if (tableOpJDBC.insert(user)) {
                    log.info("插入{}成功！", user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        try {
            List<User> users = tableOpJDBC.queryByName("jack");
            for (User user : users) {
                log.info(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        User user = new User(3, "netty-01", 10);
        try {
            tableOpJDBC.updateById(user);
            log.info("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            if (tableOpJDBC.deleteById(4)) {
                log.info("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
