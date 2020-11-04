package pers.qingxuan.gateway.proxy.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qingxuan.gateway.proxy.GatewayClient;

/**
 * <p> netty 实现
 *
 * @author : QingXuan
 * @since Created in 8:57 上午 2020/11/4
 */
public class NettyHttpClient implements GatewayClient {
    public static final Logger log = LoggerFactory.getLogger(NettyHttpClient.class);

    private final EventLoopGroup loopGroup = new NioEventLoopGroup();

    @Override
    public void doRequest(String url, FullHttpRequest request, Channel channel) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpClientCodec());
                        pipeline.addLast(new GatewayClientHandler(channel));
                    }
                });
        Hostname host = Hostname.parse(url);
        try {
            ChannelFuture future = bootstrap.connect(host.host, host.port).sync();
            future.channel().writeAndFlush(request);
            future.channel().closeFuture().addListener(future1 -> {
                log.info("{} 断开啦！", url);
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
            failure(channel);
        }
    }

    private void failure(Channel channel) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_GATEWAY);
        response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    static class Hostname {
        public final String host;
        public final int port;

        public Hostname(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public static Hostname parse(String hostname) {
            String[] strs = hostname.split(":");
            return new Hostname(strs[0], Integer.parseInt(strs[1]));
        }
    }
}
