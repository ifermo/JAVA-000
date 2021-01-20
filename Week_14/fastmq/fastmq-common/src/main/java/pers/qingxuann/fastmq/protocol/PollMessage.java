package pers.qingxuann.fastmq.protocol;

/**
 * <p> 消费者拉取的消息格式
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class PollMessage implements Message {
    private final String topic;
    private final long offset;
    private final long timestamp;
    private final byte[] message;

    public PollMessage(String topic, long offset, long timestamp, byte[] message) {
        this.topic = topic;
        this.offset = offset;
        this.timestamp = timestamp;
        this.message = message;
    }

    public long offset() {
        return offset;
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
