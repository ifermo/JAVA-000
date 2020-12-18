package pers.qingxuan.dubbo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pers.qingxuan.dubbo.entity.Rmb;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:20 2020/12/18
 */
@Mapper
@Repository
public interface RmbDao {
    int deleteByPrimaryKey(Integer mrId);

    int insert(Rmb record);

    int insertSelective(Rmb record);

    Rmb selectByPrimaryKey(Integer mrId);

    int updateByPrimaryKeySelective(Rmb record);

    int updateByPrimaryKey(Rmb record);

    Rmb selectByUserId(Integer userId);
}
