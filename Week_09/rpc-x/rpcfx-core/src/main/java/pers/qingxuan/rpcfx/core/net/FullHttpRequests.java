package pers.qingxuan.rpcfx.core.net;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.*;

import java.net.URI;

/**
 * <p> FullHttpRequest utils
 *
 * @author : QingXuan
 * @since Created in 下午10:48 2020/12/17
 */
public class FullHttpRequests {

    public static FullHttpRequest postRequest(URI uri, Object param) {
        FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uri.getPath());
        int contentLength = 0;
        if (param != null) {
            byte[] content = JSON.toJSONString(param).getBytes();
            httpRequest.content().writeBytes(content);
            contentLength = content.length;
        }
        httpRequest.headers().set(HttpHeaderNames.HOST, uri.getHost());
        httpRequest.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
        httpRequest.headers().set(HttpHeaderNames.ACCEPT, "*/*");
        httpRequest.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
        httpRequest.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
        httpRequest.headers().set(HttpHeaderNames.CONTENT_LENGTH, contentLength);
        return httpRequest;
    }
}
