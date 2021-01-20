package pers.qingxuan.fastmq.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import pers.qingxuann.fastmq.protocol.Heartbeat;

/**
 * <p> Heartbeat Handler
 *
 * @author : QingXuan
 * @since Created in 下午10:00 2021/1/16
 */
public class HeartbeatHandler extends SimpleChannelInboundHandler<Heartbeat> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Heartbeat msg) throws Exception {
        // TODO 处理心跳
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                readerIdleHandle(ctx);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    private void readerIdleHandle(ChannelHandlerContext ctx) {
        ctx.close();
    }
}
