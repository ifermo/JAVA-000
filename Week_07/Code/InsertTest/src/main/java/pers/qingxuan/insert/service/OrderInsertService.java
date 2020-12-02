package pers.qingxuan.insert.service;

import pers.qingxuan.insert.entity.OrderForm;

import java.util.List;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午7:42 2020/12/2
 */
public interface OrderInsertService {

    void insert(List<OrderForm> list)throws Exception;

}
