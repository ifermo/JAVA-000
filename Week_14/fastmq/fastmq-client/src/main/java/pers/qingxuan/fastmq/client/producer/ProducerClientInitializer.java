package pers.qingxuan.fastmq.client.producer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import pers.qingxuan.fastmq.client.handler.HeartbeatHandler;
import pers.qingxuan.fastmq.client.handler.LoginHandler;
import pers.qingxuan.fastmq.client.handler.OfferResponseHandler;
import pers.qingxuan.fastmq.client.handler.ProducerByte2MessageCodec;

/**
 * <p> fastmq producer client ChannelInitializer
 *
 * @author : Ray.fuxudong
 * @since Created in 12:35 2021/1/19
 */
public class ProducerClientInitializer extends ChannelInitializer<SocketChannel> {
    public static final int READER_IDLE_TIME_SECONDS = 10;
    public static final int WRITER_IDLE_TIME_SECONDS = 10;
    public static final int ALL_IDLE_TIME_SECONDS = 5;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new ProducerByte2MessageCodec());
        pipeline.addLast("idleDetection", new IdleStateHandler(READER_IDLE_TIME_SECONDS, WRITER_IDLE_TIME_SECONDS, ALL_IDLE_TIME_SECONDS));
        pipeline.addLast("heartbeat", new HeartbeatHandler());
        pipeline.addLast("login", new LoginHandler());
        pipeline.addLast("offer", new OfferResponseHandler());
    }
}
