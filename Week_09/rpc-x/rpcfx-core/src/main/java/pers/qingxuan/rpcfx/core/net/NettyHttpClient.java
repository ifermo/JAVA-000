package pers.qingxuan.rpcfx.core.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import pers.qingxuan.rpcfx.core.api.RpcfxException;

import java.net.URI;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

/**
 * <p> netty httpClient 实现
 *
 * @author : QingXuan
 * @since Created in 下午7:52 2020/12/17
 */
public class NettyHttpClient implements HttpClient{
    /**
     * 请求超时时间 15s
     */
    public static final long TIMEOUT = 15_000_000_000L;
    /**
     * 最大请求体大小 1MB
     */
    public static final int MAX_CONTENT_LENGTH = 1024 * 1024;

    private EventLoopGroup eventGroup;

    public NettyHttpClient() {
        this.eventGroup = new NioEventLoopGroup();
    }

    /**
     * http 方法调用
     *
     * @param url   service url
     * @param param {@link Object}
     * @return result
     */
    public String call(String url, Object param) {
        URI uri = URI.create(url);
        ResultDeposit result = new ResultDeposit();
        Bootstrap bootstrap = bootstrap(result);

        try {
            ChannelFuture future = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
            future.channel().writeAndFlush(newRequest(uri, param));
        } catch (Exception e) {
            throw new RpcfxException(e);
        }
        LockSupport.parkNanos(TIMEOUT);
        return result.content();
    }

    private Bootstrap bootstrap(Consumer<String> resultConsumer) {
        return new Bootstrap()
                .group(eventGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpClientCodec());
                        pipeline.addLast(new HttpContentDecompressor());
                        pipeline.addLast(new HttpObjectAggregator(MAX_CONTENT_LENGTH));
                        pipeline.addLast(new HttpClientHandler(resultConsumer));
                    }
                });
    }

    private FullHttpRequest newRequest(URI uri, Object param) {
        return FullHttpRequests.postRequest(uri, param);
    }

    /**
     * 资源释放
     */
    public void release() {
        if (eventGroup != null) {
            eventGroup.shutdownGracefully();
        }
        eventGroup = null;
    }
}
