package pers.qingxuan.fastmq.client.consumer;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import io.netty.util.concurrent.Future;
import pers.qingxuan.fastmq.client.ChannelCloseListener;
import pers.qingxuan.fastmq.client.FastmqClient;
import pers.qingxuan.fastmq.client.common.FastmqException;
import pers.qingxuan.fastmq.client.serialization.Deserializer;
import pers.qingxuan.fastmq.client.util.ReflectUtils;
import pers.qingxuann.fastmq.protocol.PollMessage;
import pers.qingxuann.fastmq.protocol.PollRequest;

import java.io.Closeable;
import java.net.SocketAddress;
import java.time.Duration;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * <p> a fastmq consumer client
 *
 * @author : Ray.fuxudong
 * @since Created in 10:45 2021/1/20
 */
public class FastmqConsumer<T> implements Closeable, EventHandler<MessageEvent> {
    private final int bufferSize = 10;

    private final ChannelCloseListener sentinel;
    private final ConsumerConfig config;
    private final FastmqClient client;

    private final Deserializer<T> deserializer;

    private Disruptor<MessageEvent> disruptor = new Disruptor<>(MessageEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

    private volatile boolean closed = false;

    private final Exchanger<PollMessage> exchanger = new Exchanger<>();

    @SuppressWarnings("unchecked")
    public FastmqConsumer(ConsumerConfig config) {
        try {
            this.config = config;
            SocketAddress address = config.getAddress();
            this.client = new FastmqClient(new ConsumerClientInitializer(exchanger));
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
     *
     * @return {@link ConsumerRecord}
     */
    public ConsumerRecord<T> poll(String topic, Duration duration) throws Exception {
        this.record = null;
        PollRequest request = new PollRequest(-1, topic);
        client.channel().writeAndFlush(request);
//        barrier.await(duration.getSeconds(), TimeUnit.SECONDS);
//        return record;
        PollMessage message = exchanger.exchange(null, duration.getSeconds(), TimeUnit.SECONDS);
        if (message == null) {
            return null;
        }
        T msg = deserializer.deserialize(message.topic(), message.message());
        return new ConsumerRecord<>(message.topic(), msg, message.offset(), message.timestamp());
    }

    /**
     * 提交位移
     *
     * @return {@link Future}
     */
    public Future<Void> commit() {
        return null;
    }

    private ConsumerRecord<T> record;
    private final CyclicBarrier barrier = new CyclicBarrier(2);

    @Override
    public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getTimestamp() != -1) {
            T msg = deserializer.deserialize(event.getTopic(), event.getMessage());
            record = new ConsumerRecord<>(event.getTopic(), msg, event.getOffset(), event.getTimestamp());
        }
        barrier.await();
    }
}
