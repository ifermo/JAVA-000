package pers.qingxuann.fastmq.protocol;

/**
 * <p> 心跳数据
 *
 * @author : QingXuan
 * @since Created in 下午12:04 2021/1/17
 */
public class Heartbeat implements Message{
    private long uid;
    private long timestamp;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
