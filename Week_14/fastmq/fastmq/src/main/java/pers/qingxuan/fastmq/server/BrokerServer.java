package pers.qingxuan.fastmq.server;

import com.google.common.base.Strings;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> broker server
 *
 * @author : QingXuan
 * @since Created in 下午9:43 2021/1/19
 */
public class BrokerServer {
    public static final Logger log = LoggerFactory.getLogger(BrokerServer.class);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private Channel channel;

    public BrokerServer(EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    public void start(int port) {
        start(null, port);
    }

    /**
     * start a tunnel server
     *
     * @param host server host ip
     * @param port server port
     */
    public void start(String host, int port) {
        ServerBootstrap bootstrap = serverBootstrap();
        ChannelFuture future = Strings.isNullOrEmpty(host) ?
                bootstrap.bind(port) : bootstrap.bind(host, port);
        future.addListener(future1 -> {
            if (future1.isSuccess()) {
                log.info("Tunnel server startup finish,host {} port {}", host, port);
            } else {
                log.info("Tunnel server startup failure");
            }
        });
        this.channel = future.channel();
    }

    /**
     * create ServerBootstrap
     *
     * @return {@link ServerBootstrap}
     */
    private ServerBootstrap serverBootstrap() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, false)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new BrokerChannelInitializer());
        return bootstrap;
    }

    /**
     * shutdown server channel
     */
    public void stop() {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
    }
}
