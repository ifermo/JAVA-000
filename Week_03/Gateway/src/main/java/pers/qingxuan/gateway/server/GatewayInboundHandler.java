package pers.qingxuan.gateway.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import pers.qingxuan.gateway.proxy.GatewayClient;
import pers.qingxuan.gateway.proxy.GatewayClientFactory;
import pers.qingxuan.gateway.route.DefaultHttpEndpointRouter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> gateway handler
 *
 * @author : QingXuan
 * @since Created in 9:05 下午 2020/11/3
 */
@ChannelHandler.Sharable
public class GatewayInboundHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final DefaultHttpEndpointRouter httpRouter;
    private final List<String> hosts;
    private final GatewayClient client;

    public GatewayInboundHandler() {
        httpRouter = new DefaultHttpEndpointRouter();
        hosts = new ArrayList<>();
        client = GatewayClientFactory.get();
        initHosts();
    }

    private void initHosts() {
        hosts.add("http://127.0.0.1:8081");
        hosts.add("http://127.0.0.1:8082");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        String targetHost = nextHost();
        client.doRequest(targetHost, msg, ctx.channel());
    }

    public String nextHost() {
        return httpRouter.route(hosts);
    }
}
