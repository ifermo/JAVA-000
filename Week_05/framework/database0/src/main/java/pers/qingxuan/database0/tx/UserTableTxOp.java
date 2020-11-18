package pers.qingxuan.database0.tx;

import pers.qingxuan.database0.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p> jdbc 事物操作
 *
 * @author : QingXuan
 * @since Created in 下午7:33 2020/11/18
 */
public class UserTableTxOp {
    private final Connection conn;

    public UserTableTxOp(Connection conn) {
        this.conn = conn;
    }

    public void insertAll(List<User> users) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "INSERT INTO user(`uid`,`username`,`age`) VALUES (?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        try (psmt) {
            for (User user : users) {
                psmt.setInt(1, user.getUid());
                psmt.setString(2, user.getUsername());
                psmt.setInt(3, user.getAge());
                psmt.addBatch();
            }
            psmt.executeBatch();
            // 提交事物
            conn.commit();
            conn.setAutoCommit(true);
        }
    }

}
