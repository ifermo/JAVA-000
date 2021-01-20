package pers.qingxuan.fastmq.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.qingxuann.fastmq.protocol.OfferMessage;

/**
 * <p> 消费者消费请求处理
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class MessagePollHandler extends SimpleChannelInboundHandler<OfferMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OfferMessage msg) throws Exception {

    }
}
