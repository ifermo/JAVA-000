package pers.qingxuan.database0;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qingxuan.database0.hikaricp.UserTableHiKariCP;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> jdbc 操作测试类
 *
 * @author : QingXuan
 * @since Created in 下午11:05 2020/11/17
 */
public class UserTableHiKariCPTest {
    public static final Logger log = LoggerFactory.getLogger(UserTableHiKariCPTest.class);

    private DataSource dataSource;
    private UserTableHiKariCP hiKariCPOp;

    @BeforeEach
    public void connect() throws Exception{
        HikariConfig config = new HikariConfig("/hikari.properties");
        dataSource = new HikariDataSource(config);
        hiKariCPOp=new UserTableHiKariCP(dataSource);
    }

    @AfterEach
    public void release() {
        hiKariCPOp = null;
        if (dataSource != null) {
            ((HikariDataSource)dataSource).close();
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
                if (hiKariCPOp.insert(user)) {
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
            List<User> users = hiKariCPOp.queryByName("jack");
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
            hiKariCPOp.updateById(user);
            log.info("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            if (hiKariCPOp.deleteById(4)) {
                log.info("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
