package pers.qingxuan.gateway.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Http gateway server
 *
 * @author : QingXuan
 * @since Created in 8:25 下午 2020/11/3
 */
public class HttpGatewayServer {
    public static final Logger log = LoggerFactory.getLogger(HttpGatewayServer.class);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private final int port;

    public HttpGatewayServer(int port) {
        this.port = port;
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
    }

    /**
     * start GatewayServer
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = newBootstrap();
        ChannelFuture future = bootstrap.bind(port)
                .addListener(future1 -> {
                    if (future1.isSuccess()) {
                        log.info("gateway server started! port:{}", port);
                    } else {
                        release();
                    }
                }).sync();

        future.channel().closeFuture()
                .addListener(future1 -> release());
    }

    /**
     * Release resources
     */
    public void release() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
            bossGroup = null;
            workerGroup.shutdownGracefully();
            workerGroup = null;
        }
    }

    private ServerBootstrap newBootstrap() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                .option(EpollChannelOption.SO_REUSEPORT, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new HttpGatewayInitializer());
        return bootstrap;
    }
}
