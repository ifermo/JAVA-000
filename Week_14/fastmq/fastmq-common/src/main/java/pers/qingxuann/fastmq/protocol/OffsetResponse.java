package pers.qingxuann.fastmq.protocol;

/**
 * <p> 位移提交应答
 *
 * @author : QingXuan
 * @since Created in 下午8:33 2021/1/20
 */
public class OffsetResponse implements Message {
    private final long offset;
    private final boolean ok;

    public OffsetResponse(long offset, boolean ok) {
        this.offset = offset;
        this.ok = ok;
    }

    public long offset() {
        return offset;
    }

    public boolean isOk() {
        return ok;
    }
}
