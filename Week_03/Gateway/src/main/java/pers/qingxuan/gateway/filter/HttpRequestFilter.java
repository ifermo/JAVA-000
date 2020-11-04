package pers.qingxuan.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * <p> Http Request Filter
 *
 * @author : QingXuan
 * @since Created in 8:05 下午 2020/11/3
 */
public interface HttpRequestFilter {

    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);

}
