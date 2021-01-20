package pers.qingxuan.fastmq.client.consumer;

/**
 * <p> disruptor event
 *
 * @author : QingXuan
 * @since Created in 下午10:10 2021/1/20
 */
public class MessageEvent {
    private  String topic;
    private  long offset;
    private  long timestamp;
    private  byte[] message;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public long getOffset() {
        return offset;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public byte[] getMessage() {
        return message;
    }
}
