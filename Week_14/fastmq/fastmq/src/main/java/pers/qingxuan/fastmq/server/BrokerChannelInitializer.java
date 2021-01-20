package pers.qingxuan.fastmq.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import pers.qingxuan.fastmq.handler.*;

/**
 * <p> Tunnel ChannelHandler load
 *
 * @author : QingXuan
 * @since Created in 下午9:49 2021/1/16
 */
public class BrokerChannelInitializer extends ChannelInitializer<SocketChannel> {

    public static final int READER_IDLE_TIME_SECONDS = 10;

    private final LoginHandler loginHandler = new LoginHandler();
    private final MessageOfferHandler offerHandler = new MessageOfferHandler();
    private final MessagePollHandler pollHandler = new MessagePollHandler();
    private final MessageOffsetHandler offsetHandler = new MessageOffsetHandler();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new Byte2MessageCodec());
        pipeline.addLast("idleDetection", new IdleStateHandler(READER_IDLE_TIME_SECONDS, 0, 0));
        pipeline.addLast("heartbeat", new HeartbeatHandler());
        pipeline.addLast("login", loginHandler);
        pipeline.addLast("offer", offerHandler);
        pipeline.addLast("poll", pollHandler);
        pipeline.addLast("offset", offsetHandler);
    }
}
