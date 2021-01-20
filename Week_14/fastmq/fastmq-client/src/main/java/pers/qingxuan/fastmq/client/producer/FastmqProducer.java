package pers.qingxuan.fastmq.client.producer;

import io.netty.util.concurrent.Future;
import pers.qingxuan.fastmq.client.ChannelCloseListener;
import pers.qingxuan.fastmq.client.FastmqClient;
import pers.qingxuan.fastmq.client.common.FastmqException;
import pers.qingxuan.fastmq.client.consumer.ConsumerClientInitializer;
import pers.qingxuan.fastmq.client.util.ReflectUtils;
import pers.qingxuan.fastmq.client.serialization.Serializer;

import java.io.Closeable;
import java.net.SocketAddress;

/**
 * <p> a fastmq producer client
 *
 * @author : Ray.fuxudong
 * @since Created in 10:28 2021/1/20
 */
public class FastmqProducer<T> implements Closeable {
    private final ChannelCloseListener sentinel;
    private final ProducerConfig config;
    private final FastmqClient client;

    private final Serializer<T> serializer;

    private volatile boolean closed = false;

    @SuppressWarnings("unchecked")
    public FastmqProducer(ProducerConfig config) {
        try {
            this.config = config;
            SocketAddress address = config.getAddress();
            this.client = new FastmqClient(new ConsumerClientInitializer());
            this.sentinel = new ChannelCloseListener(client, address);
            this.client.start(address).closeFuture().addListener(this.sentinel);
            this.serializer = ReflectUtils.newInstance(config.getSerializerClass(), Serializer.class);
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

    public Future<Void> send(ProducerRecord<T> record) {
        return null;
    }
}
