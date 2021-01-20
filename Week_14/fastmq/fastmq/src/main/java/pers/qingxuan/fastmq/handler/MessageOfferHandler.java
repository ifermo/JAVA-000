package pers.qingxuan.fastmq.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.qingxuann.fastmq.protocol.OfferMessage;

/**
 * <p> 生产者消息入队请求处理
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class MessageOfferHandler extends SimpleChannelInboundHandler<OfferMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OfferMessage msg) throws Exception {

    }
}
