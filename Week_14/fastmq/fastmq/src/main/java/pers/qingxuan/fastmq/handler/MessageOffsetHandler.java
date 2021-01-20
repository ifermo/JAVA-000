package pers.qingxuan.fastmq.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.qingxuann.fastmq.protocol.OfferMessage;
import pers.qingxuann.fastmq.protocol.OffsetRequest;

/**
 * <p> 消费者消费位移提交处理
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class MessageOffsetHandler extends SimpleChannelInboundHandler<OffsetRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OffsetRequest msg) throws Exception {

    }
}
