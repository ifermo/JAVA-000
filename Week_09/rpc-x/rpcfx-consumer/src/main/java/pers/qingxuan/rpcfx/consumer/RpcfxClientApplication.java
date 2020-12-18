package pers.qingxuan.rpcfx.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qingxuan.rpcfx.api.Order;
import pers.qingxuan.rpcfx.api.OrderService;
import pers.qingxuan.rpcfx.api.User;
import pers.qingxuan.rpcfx.api.UserService;
import pers.qingxuan.rpcfx.core.client.Rpcfx;

public class RpcfxClientApplication {
    public static final Logger log = LoggerFactory.getLogger(RpcfxClientApplication.class);

    public static void main(String[] args) throws Exception {

        UserService userService = Rpcfx.create(UserService.class, "http://127.0.0.1:8080/");
        User user = userService.findById(1);
		log.info("find user id=1 from server: {}",user.getName());

        OrderService orderService = Rpcfx.create(OrderService.class, "http://127.0.0.1:8080/");
        Order order = orderService.findOrderById(1992129);
		log.info("find order name={}, amount={}",order.getName(), order.getAmount());
    }

}
