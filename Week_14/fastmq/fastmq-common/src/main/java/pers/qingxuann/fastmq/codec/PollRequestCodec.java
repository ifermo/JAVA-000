package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import pers.qingxuann.fastmq.protocol.Message;
import pers.qingxuann.fastmq.protocol.PollMessage;
import pers.qingxuann.fastmq.protocol.PollRequest;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static pers.qingxuann.fastmq.common.Constant.*;

/**
 * </p>
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class PollRequestCodec implements MessageCodec {

    @Override
    public byte type() {
        return POLL_REQUEST;
    }

    @Override
    public ByteBuf write(Message obj) {
        PollRequest request = (PollRequest) obj;
        ByteBuf buf = buffer();
        // write offset
        buf.writeLong(request.offset());
        // write topic
        byte[] topic = request.topic().getBytes(CHARSET);
        buf.writeBytes(topic);
        return buf;
    }

    @Override
    public Message read(ByteBuf buf) {
        // read offset
        long offset = buf.readLong();
        // read topic
        byte[] topic = new byte[buf.readableBytes()];
        buf.readBytes(topic);
        return new PollRequest(offset, new String(topic, CHARSET));
    }

}
