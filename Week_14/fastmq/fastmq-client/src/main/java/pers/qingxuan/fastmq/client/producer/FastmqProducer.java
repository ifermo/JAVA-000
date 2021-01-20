package pers.qingxuan.fastmq.client.producer;

import io.netty.util.concurrent.Future;
import pers.qingxuan.fastmq.client.ChannelCloseListener;
import pers.qingxuan.fastmq.client.FastmqClient;
import pers.qingxuan.fastmq.client.common.FastmqException;
import pers.qingxuan.fastmq.client.consumer.ConsumerClientInitializer;
import pers.qingxuan.fastmq.client.util.ReflectUtils;
import pers.qingxuan.fastmq.client.serialization.Serializer;
import pers.qingxuann.fastmq.protocol.OfferMessage;
import pers.qingxuann.fastmq.protocol.OfferResponse;

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
            this.client = new FastmqClient(new ProducerClientInitializer());
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

    /**
     * 发送消息
     *
     * @param record {@link ProducerRecord}
     * @return {@link Future}
     */
    public Future<Void> send(ProducerRecord<T> record) {
        byte[] bytes = serializer.serialize(record.getTopic(), record.getMessage());
        OfferMessage message = new OfferMessage(record.getTopic(), System.currentTimeMillis(), bytes);
        return client.channel().writeAndFlush(message);
    }
}
