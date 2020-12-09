package pers.qingxuan.subtable.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.qingxuan.subtable.entity.Order;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {
    int deleteByPrimaryKey(Integer alarmId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer alarmId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectByUserId(@Param("userId")Integer userId,@Param("limitCount")Integer limitCount);
}