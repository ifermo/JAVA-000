package pers.qingxuan.database0;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:30 2020/11/17
 */
public interface UserTableOp {

    List<User> queryByName(String username) throws SQLException;

    boolean insert(User user) throws SQLException;

    boolean deleteById(int uid) throws SQLException;

    boolean updateById(User user) throws SQLException;
}
