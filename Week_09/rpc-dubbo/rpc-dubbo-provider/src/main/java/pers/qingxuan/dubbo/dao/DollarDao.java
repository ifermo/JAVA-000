package pers.qingxuan.dubbo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pers.qingxuan.dubbo.entity.Dollar;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:20 2020/12/18
 */
@Mapper
@Repository
public interface DollarDao {
    int deleteByPrimaryKey(Integer mdId);

    int insert(Dollar record);

    int insertSelective(Dollar record);

    Dollar selectByPrimaryKey(Integer mdId);

    int updateByPrimaryKeySelective(Dollar record);

    int updateByPrimaryKey(Dollar record);

    Dollar selectByUserId(Integer userId);
}
