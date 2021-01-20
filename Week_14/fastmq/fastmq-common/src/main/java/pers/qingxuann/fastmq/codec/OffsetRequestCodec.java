package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import pers.qingxuann.fastmq.protocol.Message;
import pers.qingxuann.fastmq.protocol.OffsetRequest;

import static pers.qingxuann.fastmq.common.Constant.OFFSET_COMMIT;

/**
 * <p> 位移提交请求
 *
 * @author : QingXuan
 * @since Created in 下午8:33 2021/1/20
 */
public class OffsetRequestCodec implements MessageCodec {
    @Override
    public byte type() {
        return OFFSET_COMMIT;
    }

    @Override
    public ByteBuf write(Message message) {
        OffsetRequest request = (OffsetRequest) message;
        ByteBuf buf = buffer();
        buf.writeLong(request.offset());
        buf.writeLong(request.timestamp());
        return buf;
    }

    @Override
    public Message read(ByteBuf buf) {
        long offset = buf.readLong();
        long timestamp = buf.readLong();
        return new OffsetRequest(offset,timestamp);
    }
}
