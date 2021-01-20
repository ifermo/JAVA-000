package pers.qingxuan.fastmq;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import pers.qingxuan.fastmq.common.BeanLifecycle;
import pers.qingxuan.fastmq.server.BrokerServer;

/**
 * <p> broker bootstrap
 *
 * @author : QingXuan
 * @since Created in 下午8:02 2021/1/20
 */
@Configuration
public class BrokerBootstrap implements BeanLifecycle {
    @Value("${listener.port}")
    private int port;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private BrokerServer server;

    @Override
    public void init() {
        this.bossGroup = new NioEventLoopGroup(1);
        this.workerGroup = new NioEventLoopGroup();
        this.server = new BrokerServer(bossGroup, workerGroup);
        server.start(port);
    }

    @Override
    public void destroy() {
        server.stop();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
