package pers.qingxuan.gateway.proxy;

import pers.qingxuan.gateway.proxy.netty.NettyHttpClient;
import pers.qingxuan.gateway.proxy.okhttp.OkHttpClient;

import java.util.Objects;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 6:47 下午 2020/11/4
 */
public class GatewayClientFactory {
    public static GatewayClient get() {
        String clientType = System.getProperty("client-type");
        if (Objects.equals("netty", clientType)) {
            return new NettyHttpClient();
        }
        return new OkHttpClient();
    }
}
