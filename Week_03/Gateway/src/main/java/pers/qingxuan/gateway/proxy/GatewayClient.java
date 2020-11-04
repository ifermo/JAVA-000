package pers.qingxuan.gateway.proxy;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.*;


/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 8:23 下午 2020/11/3
 */
public interface GatewayClient {

    void doRequest(String url, FullHttpRequest httpHeaders, Channel channel);

}
