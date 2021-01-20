package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import pers.qingxuann.fastmq.protocol.Message;
import pers.qingxuann.fastmq.protocol.OfferMessage;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static pers.qingxuann.fastmq.common.Constant.*;

/**
 * <p> 生产者消息编码器
 * 数据帧编码格式形如：
 * |--Topic Length--|--Topic--|--Timestamp--|--Message Length--|--Message--|
 *
 * @author : Ray.fuxudong
 * @since Created in 16:31 2021/1/20
 */
public class OfferMessageCodec implements MessageCodec {

    @Override
    public byte type() {
        return SEND_REQUEST;
    }

    @Override
    public ByteBuf write(Message obj) {
        OfferMessage message = (OfferMessage) obj;
        ByteBuf buf = buffer();
        byte[] topic = message.topic().getBytes(CHARSET);
        // write topic
        buf.writeInt(topic.length);
        buf.writeBytes(topic);
        // write timestamp
        buf.writeLong(message.timestamp());
        // write message
        buf.writeInt(message.message().length);
        buf.writeBytes(message.message());
        return buf;
    }

    @Override
    public Message read(ByteBuf buf) {
        // read topic
        int topicLength = buf.readInt();
        byte[] topic = new byte[topicLength];
        buf.readBytes(topic);
        // read timestamp
        long timestamp = buf.readLong();
        // read message
        int msgLength = buf.readInt();
        byte[] message = new byte[msgLength];
        buf.readBytes(message);

        return new OfferMessage(new String(topic, CHARSET), timestamp, message);
    }

}
