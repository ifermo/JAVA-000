package pers.qingxuan.fastmq.client.consumer;

/**
 * <p> fastmq consumer record
 *
 * @author : Ray.fuxudong
 * @since Created in 12:20 2021/1/20
 */
public class ConsumerRecord<T> {
    /**
     * 主题
     */
    private final String topic;
    /**
     * 消息
     */
    private final T message;
    /**
     * 消费位移
     */
    private final long offset;
    /**
     * 时间戳
     */
    private final long timestamp;

    public ConsumerRecord(String topic, T message, long offset, long timestamp) {
        this.topic = topic;
        this.message = message;
        this.offset = offset;
        this.timestamp = timestamp;
    }

    public String getTopic() {
        return topic;
    }

    public T getMessage() {
        return message;
    }

    public long getOffset() {
        return offset;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
