package pers.qingxuan.gateway.route;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> 默认路由实现
 *
 * @author : QingXuan
 * @since Created in 8:28 上午 2020/11/4
 */
public class DefaultHttpEndpointRouter implements HttpEndpointRouter {

    private final AtomicInteger idx = new AtomicInteger(0);

    @Override
    public String route(List<String> endpoints) {
        int dx=idx.getAndIncrement();
        return endpoints.get(dx % endpoints.size());
    }
}
