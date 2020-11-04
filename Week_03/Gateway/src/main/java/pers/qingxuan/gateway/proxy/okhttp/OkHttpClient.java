package pers.qingxuan.gateway.proxy.okhttp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.*;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import pers.qingxuan.gateway.proxy.GatewayClient;

import java.io.IOException;

/**
 * <p> OkHttp 实现
 *
 * @author : QingXuan
 * @since Created in 9:10 下午 2020/11/3
 */
public class OkHttpClient implements GatewayClient {
    private final okhttp3.OkHttpClient client;

    public OkHttpClient() {
        client = new okhttp3.OkHttpClient();
    }

    public void doRequest(String host, FullHttpRequest request, Channel channel) {
        String url = host + request.uri();
        HttpMethod method = request.method();
        if (method == HttpMethod.GET) {
            doGet(url, request, channel);
        } else if (method == HttpMethod.POST) {
            doPost(url, request, channel);
        } else {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.METHOD_NOT_ALLOWED);
            response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * create Request.Builder
     *
     * @param headers {@link HttpHeaders}
     * @return {@link Request.Builder}
     */
    private Request.Builder builder(HttpHeaders headers) {
        Request.Builder builder = new Request.Builder();
        headers.entries().forEach(item -> {
            builder.addHeader(item.getKey(), item.getValue());
        });
        return builder;
    }

    private void doGet(String url, FullHttpRequest request, Channel channel) {
        Request request0 = builder(request.headers())
                .get()
                .url(url)
                .build();

        client.newCall(request0).enqueue(new Callback0(channel));
    }

    private void doPost(String url, FullHttpRequest request, Channel channel) {
        ByteBuf byteBuf = request.content();
        RequestBody requestBody;
        if (byteBuf.isDirect()) {
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            requestBody = RequestBody.create(bytes);
        } else {
            requestBody = RequestBody.create(byteBuf.array());
        }

        Request request0 = builder(request.headers())
                .post(requestBody)
                .url(url)
                .build();

        client.newCall(request0).enqueue(new Callback0(channel));
    }

    public static class Callback0 implements Callback {

        private final Channel channel;

        public Callback0(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeBytes(e.getMessage().getBytes());
            reply(channel, HttpResponseStatus.BAD_GATEWAY, byteBuf);
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            HttpResponseStatus status = HttpResponseStatus.valueOf(response.code());
            ResponseBody body = response.body();
            if (body != null) {
                ByteBuf byteBuf = Unpooled.buffer();
                byteBuf.writeBytes(body.bytes());
                reply(channel, status, byteBuf);
            } else {
                reply(channel, status, Unpooled.buffer(0));
            }
        }

        private void reply(Channel channel, HttpResponseStatus status, ByteBuf byteBuf) {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, byteBuf);
            response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
