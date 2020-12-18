package pers.qingxuan.rpcfx.core.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * <p> HttpClient InboundHandler
 *
 * @author : QingXuan
 * @since Created in 下午6:35 2020/12/16
 */
public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private final Consumer<String> consumer;

    public HttpClientHandler(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
        String result = msg.content().toString(StandardCharsets.UTF_8);
        consumer.accept(result);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ByteBuf buf = Unpooled.copiedBuffer(cause.getMessage().getBytes());
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_GATEWAY, buf);
        response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        ctx.channel().writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        ctx.close();
    }
}
