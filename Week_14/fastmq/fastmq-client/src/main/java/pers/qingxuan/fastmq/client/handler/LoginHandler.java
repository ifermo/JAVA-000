package pers.qingxuan.fastmq.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.qingxuann.fastmq.protocol.LoginRequest;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:59 2021/1/19
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) throws Exception {

    }
}
