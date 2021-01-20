package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import pers.qingxuann.fastmq.protocol.Heartbeat;
import pers.qingxuann.fastmq.protocol.Message;

import static pers.qingxuann.fastmq.common.Constant.HEARTBEAT;

/**
 * <p> heartbeat 消息编解码
 *
 * @author : QingXuan
 * @since Created in 下午1:09 2021/1/17
 */
public class HeartbeatCodec implements MessageCodec {

    @Override
    public byte type() {
        return HEARTBEAT;
    }

    @Override
    public Message read(ByteBuf buf) {
        Heartbeat result = new Heartbeat();
        result.setUid(buf.readLong());
        result.setTimestamp(buf.readLong());
        return result;
    }

    @Override
    public ByteBuf write(Message obj) {
        if (!(obj instanceof Heartbeat)) {
            throw new CodecException();
        }

        Heartbeat heartbeat = (Heartbeat) obj;
        ByteBuf result = buffer();
        result.writeLong(heartbeat.getUid());
        result.writeLong(heartbeat.getTimestamp());
        return result;
    }
}
