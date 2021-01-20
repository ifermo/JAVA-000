package pers.qingxuan.fastmq.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import pers.qingxuan.fastmq.handler.Byte2MessageCodec;
import pers.qingxuan.fastmq.handler.HeartbeatHandler;
import pers.qingxuan.fastmq.handler.LoginHandler;

/**
 * <p> Tunnel ChannelHandler load
 *
 * @author : QingXuan
 * @since Created in 下午9:49 2021/1/16
 */
public class BrokerChannelInitializer extends ChannelInitializer<SocketChannel> {

    public static final int READER_IDLE_TIME_SECONDS = 10;

    private final LoginHandler loginHandler = new LoginHandler();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new Byte2MessageCodec());
        pipeline.addLast("idleDetection", new IdleStateHandler(READER_IDLE_TIME_SECONDS, 0, 0));
        pipeline.addLast("heartbeat", new HeartbeatHandler());
        pipeline.addLast("login", loginHandler);
    }
}
