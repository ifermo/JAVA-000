package pers.qingxuann.fastmq.protocol;

/**
 * <p> 生产者消息入读响应
 *
 * @author : QingXuan
 * @since Created in 下午8:14 2021/1/20
 */
public class OfferResponse implements Message {
    private final boolean ok;
    private final long offset;
    private final long timestamp;
    private final String topic;

    public OfferResponse(boolean ok, long offset, long timestamp, String topic) {
        this.ok = ok;
        this.offset = offset;
        this.timestamp = timestamp;
        this.topic = topic;
    }

    public boolean isOk() {
        return ok;
    }

    public long offset() {
        return offset;
    }

    public long timestamp() {
        return timestamp;
    }

    public String topic() {
        return topic;
    }
}
