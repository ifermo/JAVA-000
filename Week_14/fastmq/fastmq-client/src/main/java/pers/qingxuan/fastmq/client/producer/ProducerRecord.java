package pers.qingxuan.fastmq.client.producer;

/**
 * <p> fastmq producer record
 *
 * @author : Ray.fuxudong
 * @since Created in 12:24 2021/1/20
 */
public class ProducerRecord<T> {
    /**
     * 主题
     */
    private final String topic;
    /**
     * 消息
     */
    private final T message;
    /**
     * 时间戳
     */
    private final long timestamp;

    public ProducerRecord(String topic, T message, long timestamp) {
        this.topic = topic;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getTopic() {
        return topic;
    }

    public T getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
