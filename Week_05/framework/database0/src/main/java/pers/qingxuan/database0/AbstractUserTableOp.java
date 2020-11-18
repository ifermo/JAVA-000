package pers.qingxuan.database0;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> UserTableOp 实现
 *
 * @author : QingXuan
 * @since Created in 下午7:00 2020/11/18
 */
public abstract class AbstractUserTableOp implements UserTableOp{

    /**
     * 提供 Connection 连接
     * @return {@link Connection}
     */
    public abstract Connection getConnection() throws SQLException;

    @Override
    public List<User> queryByName(String username) throws SQLException {
        String sql = "SELECT `uid`,`username`,`age` FROM `user` WHERE username=?";
        PreparedStatement psmt = getConnection().prepareStatement(sql);
        psmt.setString(1, username);
        ResultSet resSet = psmt.executeQuery();
        List<User> users = new LinkedList<>();
        while (resSet.next()) {
            User user = new User();
            user.setUid(resSet.getInt("uid"));
            user.setUsername(resSet.getString("username"));
            user.setAge(resSet.getInt("age"));
            users.add(user);
        }
        resSet.close();
        psmt.close();
        return users;
    }

    @Override
    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO user(`uid`,`username`,`age`) VALUES (?,?,?)";
        PreparedStatement psmt = getConnection().prepareStatement(sql);
        try (psmt) {
            psmt.setInt(1, user.getUid());
            psmt.setString(2, user.getUsername());
            psmt.setInt(3, user.getAge());
            return psmt.execute();
        }
    }

    @Override
    public boolean deleteById(int uid) throws SQLException {
        String sql = "DELETE FROM user WHERE `uid`=?";
        try (PreparedStatement psmt = getConnection().prepareStatement(sql)) {
            psmt.setInt(1, uid);
            return psmt.execute();
        }
    }

    @Override
    public boolean updateById(User user) throws SQLException {
        String sql = String.format("UPDATE `user` SET `username` = %s,`age` = %d WHERE `uid` = %d",
                user.getUsername(), user.getAge(), user.getUid());
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        }
    }
}
