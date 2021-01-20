package pers.qingxuan.fastmq.client.consumer;

import pers.qingxuan.fastmq.client.ChannelCloseListener;
import pers.qingxuan.fastmq.client.FastmqClient;
import pers.qingxuan.fastmq.client.common.FastmqException;
import pers.qingxuan.fastmq.client.serialization.Deserializer;
import pers.qingxuan.fastmq.client.serialization.Serializer;
import pers.qingxuan.fastmq.client.util.ReflectUtils;

import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.Future;

/**
 * <p> a fastmq consumer client
 *
 * @author : Ray.fuxudong
 * @since Created in 10:45 2021/1/20
 */
public class FastmqConsumer<T> implements Closeable {
    private final ChannelCloseListener sentinel;
    private final ConsumerConfig config;
    private final FastmqClient client;

    private final Deserializer<T> deserializer;

    private volatile boolean closed = false;

    @SuppressWarnings("unchecked")
    public FastmqConsumer(ConsumerConfig config) {
        try {
            this.config = config;
            SocketAddress address = config.getAddress();
            this.client = new FastmqClient(new ConsumerClientInitializer());
            this.sentinel = new ChannelCloseListener(client, address);
            this.client.start(address).closeFuture().addListener(this.sentinel);
            this.deserializer = ReflectUtils.newInstance(config.getDeserializerClass(), Deserializer.class);
        } catch (Throwable t) {
            close();
            throw new FastmqException("Failed to construct fastmq producer", t);
        }
    }

    @Override
    public void close() {
        this.sentinel.close();
        this.client.close();
    }

    /**
     * 拉取消息
     * @return {@link ConsumerRecord}
     */
    public ConsumerRecord<T> poll() {
        return null;
    }

    /**
     * 提交位移
     * @return {@link Future}
     */
    public Future<Void> commit() {
        return null;
    }
}
