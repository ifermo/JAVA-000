package pers.qingxuan.fastmq.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.qingxuann.fastmq.protocol.OfferResponse;
import pers.qingxuann.fastmq.protocol.OffsetResponse;

/**
 * <p> OfferResponse Handler
 *
 * @author : QingXuan
 * @since Created in 下午9:02 2021/1/20
 */
public class OffsetResponseHandler extends SimpleChannelInboundHandler<OffsetResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OffsetResponse msg) throws Exception {

    }
}
