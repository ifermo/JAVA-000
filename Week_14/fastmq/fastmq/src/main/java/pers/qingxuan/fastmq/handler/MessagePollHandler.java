package pers.qingxuan.fastmq.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.qingxuan.fastmq.FastmqMessage;
import pers.qingxuan.fastmq.FastmqQueue;
import pers.qingxuan.fastmq.FastmqStore;
import pers.qingxuann.fastmq.protocol.PollMessage;
import pers.qingxuann.fastmq.protocol.PollRequest;

/**
 * <p> 消费者消费请求处理
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class MessagePollHandler extends SimpleChannelInboundHandler<PollRequest> {
    private final FastmqStore fastmqStore = FastmqStore.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PollRequest msg) throws Exception {
        FastmqQueue queue = fastmqStore.getQueue(msg.topic());
        FastmqMessage message = queue.dequeue();
        if (message == null) {
            ctx.writeAndFlush(new PollMessage(msg.topic(), -1, -1, new byte[0]));
        } else {
            ctx.writeAndFlush(new PollMessage(msg.topic(), -1, message.getTimestamp(), message.getMessage()));
        }
    }
}
