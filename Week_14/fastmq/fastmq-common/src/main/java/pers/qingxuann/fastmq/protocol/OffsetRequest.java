package pers.qingxuann.fastmq.protocol;

/**
 * <p> 位移提交请求
 *
 * @author : QingXuan
 * @since Created in 下午8:33 2021/1/20
 */
public class OffsetRequest implements Message {
    private final long offset;
    private final long timestamp;

    public OffsetRequest(long offset, long timestamp) {
        this.offset = offset;
        this.timestamp = timestamp;
    }

    public long offset() {
        return offset;
    }

    public long timestamp() {
        return timestamp;
    }
}
