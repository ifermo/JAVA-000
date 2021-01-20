package pers.qingxuann.fastmq.protocol;

/**
 * <p> 消费者拉取消息请求
 *
 * @author : QingXuan
 * @since Created in 下午8:12 2021/1/20
 */
public class PollRequest implements Message {
    private final long offset;
    private final String topic;

    public PollRequest(long offset, String topic) {
        this.offset = offset;
        this.topic = topic;
    }

    public long offset() {
        return this.offset;
    }

    public String topic() {
        return this.topic;
    }
}
