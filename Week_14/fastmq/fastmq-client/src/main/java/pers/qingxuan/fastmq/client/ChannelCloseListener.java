package pers.qingxuan.fastmq.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static pers.qingxuan.fastmq.client.common.Constant.*;

/**
 * <p> 客户端连接守护
 *
 * @author : Ray.fuxudong
 * @since Created in 10:51 2021/1/20
 */
public class ChannelCloseListener implements ChannelFutureListener, Closeable {

    private volatile boolean closed = false;
    private final ScheduledExecutorService scheduledExecutor = new ScheduledThreadPoolExecutor(1);
    private static final int firstDelay = 1;
    private static final int notFirstDelay = 5;

    private final FastmqClient client;
    private final SocketAddress address;

    public ChannelCloseListener(FastmqClient client, SocketAddress address) {
        this.client = client;
        this.address = address;
    }

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        Channel channel = future.channel();
        if (closed || channel.isOpen()) {
            return;
        }

        // 根据不同情况，确定连接重试的延迟时间
        int delay = channel.hasAttr(SUC) ? firstDelay : notFirstDelay;
        scheduledExecutor.schedule(() -> {
            client.start(address);
        }, delay, TimeUnit.SECONDS);
    }

    public void close() {
        this.closed = true;
    }
}
