package pers.qingxuan.rpcfx.provider.service;


import pers.qingxuan.rpcfx.api.Order;
import pers.qingxuan.rpcfx.api.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(Integer id) {
        return new Order(id, "Order:" + System.currentTimeMillis(), 9.9f);
    }
}
