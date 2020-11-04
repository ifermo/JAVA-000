package pers.qingxuan.gateway.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import pers.qingxuan.gateway.filter.DefaultHttpRequestFilter;

/**
 * <p> Http gateway initializer
 *
 * @author : QingXuan
 * @since Created in 8:54 下午 2020/11/3
 */
public class HttpGatewayInitializer extends ChannelInitializer<NioSocketChannel> {

    private final DefaultHttpRequestFilter httpRequestFilter;
    private final GatewayInboundHandler gatewayHandler;

    public HttpGatewayInitializer() {
        httpRequestFilter = DefaultHttpRequestFilter.simpleFilter();
        gatewayHandler = new GatewayInboundHandler();
    }

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast("http-codec", new HttpServerCodec());
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
        pipeline.addLast("http-aggregator", new HttpObjectAggregator(64 * 1024));
        pipeline.addLast("filter", httpRequestFilter);
        pipeline.addLast("gateway", gatewayHandler);
    }
}
