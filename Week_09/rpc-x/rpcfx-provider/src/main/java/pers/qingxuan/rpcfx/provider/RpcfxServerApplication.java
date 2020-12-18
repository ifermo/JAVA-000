package pers.qingxuan.rpcfx.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.qingxuan.rpcfx.api.OrderService;
import pers.qingxuan.rpcfx.api.UserService;
import pers.qingxuan.rpcfx.core.api.RpcfxRequest;
import pers.qingxuan.rpcfx.core.api.RpcfxResolver;
import pers.qingxuan.rpcfx.core.api.RpcfxResponse;
import pers.qingxuan.rpcfx.core.server.RpcfxInvoker;
import pers.qingxuan.rpcfx.provider.service.OrderServiceImpl;
import pers.qingxuan.rpcfx.provider.service.UserServiceImpl;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcfxServerApplication.class, args);
	}

	@Autowired
	RpcfxInvoker invoker;

	@PostMapping("/")
	public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
		return invoker.invoke(request);
	}

	@PostMapping("/index")
	public Object index() {
		return "Hello World";
	}

	@Bean
	public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
		return new RpcfxInvoker(resolver);
	}

	@Bean
	public RpcfxResolver createResolver(){
		return new ServiceResolver();
	}

	// 能否去掉name
	//
	@Bean(name = "pers.qingxuan.rpcfx.api.UserService")
	public UserService createUserService(){
		return new UserServiceImpl();
	}

	@Bean(name = "pers.qingxuan.rpcfx.api.OrderService")
	public OrderService createOrderService(){
		return new OrderServiceImpl();
	}

}
