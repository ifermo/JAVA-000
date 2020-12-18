package pers.qingxuan.dubbo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pers.qingxuan.dubbo.entity.Freeze;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:20 2020/12/18
 */
@Mapper
@Repository
public interface FreezeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Freeze record);

    int insertSelective(Freeze record);

    Freeze selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Freeze record);

    int updateByPrimaryKey(Freeze record);

    void updateByUserIdSelective(Freeze freeze);
}
