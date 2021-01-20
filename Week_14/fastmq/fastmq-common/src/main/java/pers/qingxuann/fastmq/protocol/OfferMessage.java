package pers.qingxuann.fastmq.protocol;

/**
 * <p> 生产者发送的消息格式
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class OfferMessage implements Message {
    private final String topic;
    private final long timestamp;
    private final byte[] message;

    public OfferMessage(String topic, long timestamp, byte[] message) {
        this.topic = topic;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String topic() {
        return topic;
    }

    public long timestamp() {
        return timestamp;
    }

    public byte[] message() {
        return message;
    }
}
