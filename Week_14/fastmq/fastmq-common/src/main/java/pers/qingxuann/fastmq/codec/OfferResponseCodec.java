package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import pers.qingxuann.fastmq.protocol.Message;
import pers.qingxuann.fastmq.protocol.OfferResponse;

import static pers.qingxuann.fastmq.common.Constant.CHARSET;
import static pers.qingxuann.fastmq.common.Constant.SEND_RESPONSE;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:16 2021/1/20
 */
public class OfferResponseCodec implements MessageCodec {

    @Override
    public byte type() {
        return SEND_RESPONSE;
    }

    @Override
    public ByteBuf write(Message message) {
        OfferResponse response = (OfferResponse) message;
        ByteBuf buf = buffer();
        buf.writeBoolean(response.isOk());
        buf.writeLong(response.offset());
        buf.writeLong(response.timestamp());
        byte[] topic = response.topic().getBytes(CHARSET);
        buf.writeBytes(topic);
        return buf;
    }

    @Override
    public Message read(ByteBuf buf) {
        boolean ok = buf.readBoolean();
        long offset = buf.readLong();
        long timestamp = buf.readLong();
        byte[] bytes = new byte[buf.readableBytes()];
        return new OfferResponse(ok, offset, timestamp, new String(bytes, CHARSET));
    }
}
