package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import pers.qingxuann.fastmq.protocol.Message;
import pers.qingxuann.fastmq.protocol.OffsetResponse;

import static pers.qingxuann.fastmq.common.Constant.OFFSET_RESPONSE;

/**
 * <p> 位移提交应答
 *
 * @author : QingXuan
 * @since Created in 下午8:33 2021/1/20
 */
public class OffsetResponseCodec implements MessageCodec {

    @Override
    public byte type() {
        return OFFSET_RESPONSE;
    }

    @Override
    public ByteBuf write(Message message) {
        OffsetResponse response = (OffsetResponse) message;
        ByteBuf buf = buffer();
        buf.writeLong(response.offset());
        buf.writeBoolean(response.isOk());
        return buf;
    }

    @Override
    public Message read(ByteBuf buf) {
        long offset = buf.readLong();
        boolean ok = buf.readBoolean();
        return new OffsetResponse(offset, ok);
    }
}
