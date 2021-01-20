package pers.qingxuan.fastmq.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.qingxuan.fastmq.FastmqMessage;
import pers.qingxuan.fastmq.FastmqQueue;
import pers.qingxuan.fastmq.FastmqStore;
import pers.qingxuann.fastmq.protocol.OfferMessage;
import pers.qingxuann.fastmq.protocol.OfferResponse;

/**
 * <p> 生产者消息入队请求处理
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class MessageOfferHandler extends SimpleChannelInboundHandler<OfferMessage> {
    private final FastmqStore fastmqStore = FastmqStore.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OfferMessage msg) throws Exception {
        FastmqQueue queue = fastmqStore.getQueue(msg.topic());
        FastmqMessage message = new FastmqMessage(msg.timestamp(), msg.message());

        boolean ok = queue.enqueue(message);
        OfferResponse response = new OfferResponse(ok, -1, msg.timestamp(), msg.topic());
        ctx.writeAndFlush(response);
    }
}
