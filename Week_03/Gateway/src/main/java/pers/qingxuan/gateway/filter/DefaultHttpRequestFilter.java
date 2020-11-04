package pers.qingxuan.gateway.filter;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 9:12 下午 2020/11/3
 */
@ChannelHandler.Sharable
public class DefaultHttpRequestFilter extends ChannelInboundHandlerAdapter
        implements HttpRequestFilter {

    private final Map<String, String> extHeaders;

    public DefaultHttpRequestFilter(Map<String, String> extHeaders) {
        this.extHeaders = extHeaders;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            filter((FullHttpRequest) msg, ctx);
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders headers = fullRequest.headers();
        extHeaders.forEach(headers::add);
    }

    public static DefaultHttpRequestFilter simpleFilter() {
        Map<String, String> extHeaders = new HashMap<>();
        extHeaders.put("nio", "Qing Xuan");
        return new DefaultHttpRequestFilter(extHeaders);
    }
}
