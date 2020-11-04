package pers.qingxuan.gateway.proxy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * <p> netty gateway handler
 *
 * @author : QingXuan
 * @since Created in 2:11 下午 2020/11/4
 */
public class GatewayClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private final Channel channel;

    public GatewayClientHandler(Channel channel) {
        this.channel = channel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
        channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ByteBuf buf = Unpooled.copiedBuffer(cause.getMessage().getBytes());
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_GATEWAY, buf);
        response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        ctx.close();
    }
}
