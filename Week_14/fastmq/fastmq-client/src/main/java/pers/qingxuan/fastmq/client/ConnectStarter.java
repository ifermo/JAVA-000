package pers.qingxuan.fastmq.client;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * <p> 客户端连接启动
 *
 * @author : Ray.fuxudong
 * @since Created in 11:35 2021/1/20
 */
public interface ConnectStarter {
    /**
     * 开启socket连接
     *
     * @param host ip
     * @param port post
     * @return {@link Channel}
     */
    default Channel start(String host, int port) {
        return start(new InetSocketAddress(host, port));
    }

    /**
     * 开启socket连接
     *
     * @param address 服务器地址 {@link SocketAddress}
     * @return {@link Channel}
     */
    Channel start(SocketAddress address);
}
