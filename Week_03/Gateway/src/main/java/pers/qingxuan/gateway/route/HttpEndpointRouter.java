package pers.qingxuan.gateway.route;

import java.util.List;

/**
 * <p> 路由
 *
 * @author : QingXuan
 * @since Created in 8:05 下午 2020/11/3
 */
public interface HttpEndpointRouter {

    String route(List<String> endpoints);

}
