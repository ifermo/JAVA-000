package pers.qingxuan.fastmq.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.net.SocketAddress;

import static pers.qingxuan.fastmq.client.common.Constant.*;

/**
 * <p> tunnel client
 *
 * @author : Ray
 * @since Created in 9:27 PM 1/17/21
 */
public class FastmqClient implements ConnectStarter, Closeable {
    public static final Logger log = LoggerFactory.getLogger(FastmqClient.class);

    private EventLoopGroup workerGroup = new NioEventLoopGroup(1);
    private final ChannelInitializer<? extends Channel> channelInitializer;

    private Channel channel;

    public FastmqClient(ChannelInitializer<? extends Channel> channelInitializer) {
        this.channelInitializer = channelInitializer;
    }

    @Override
    public Channel start(SocketAddress address) {
        Bootstrap bootstrap = bootstrap();
        ChannelFuture future = bootstrap.connect(address);
        future.addListener(future1 -> {
            if (future1.isSuccess()) {
                future.channel().attr(SUC).set(true);
                log.info("Tunnel client connect finish!");
            } else {
                log.error("Tunnel client connect failure!");
            }
        });
        this.channel = future.channel();
        return this.channel;
    }

    public Channel channel() {
        return this.channel;
    }

    /**
     * create Bootstrap
     *
     * @return {@link Bootstrap}
     */
    private Bootstrap bootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, false)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(channelInitializer);
        return bootstrap;
    }

    /**
     * release resource shutdown workerGroup {@link EventLoopGroup}
     */
    @Override
    public void close() {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
        if (workerGroup == null || workerGroup.isShutdown()) {
            return;
        }
        workerGroup.shutdownGracefully();
        workerGroup = null;
    }
}
