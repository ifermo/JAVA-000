package pers.qingxuan.fastmq.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import pers.qingxuann.fastmq.codec.*;
import pers.qingxuann.fastmq.protocol.*;

import java.util.List;
import java.util.Map;

import static pers.qingxuann.fastmq.common.Constant.MAGIC_NUMBER;

/**
 * <p> client ByteToMessageCodec
 *
 * @author : QingXuan
 * @since Created in 下午8:51 2021/1/20
 */
public abstract class ClientByte2MessageCodec extends ByteToMessageCodec<Message> {

    public abstract Map<Byte, MessageCodec> decoders();

    public abstract Map<Class<?>, MessageCodec> encoders();

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        MessageCodec encoder = decoders().get(msg.getClass());
        if (encoder == null) {
            return;
        }
        out.writeByte(MAGIC_NUMBER);
        out.writeByte(encoder.type());
        ByteBuf buffer = encoder.write(msg);
        out.writeInt(buffer.readableBytes());
        out.writeBytes(buffer);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte magicNumber = in.readByte();
        // 校验魔数
        if (magicNumber != MAGIC_NUMBER) {
            invalidMessageHandle(ctx);
            return;
        }
        MessageCodec decoder = decoders().get(in.readByte());
        if (decoder == null) {
            return;
        }
        // 跳过帧长度标识部分
        in.skipBytes(4);
        Message message = decoder.read(in);
        out.add(message);
    }

    /**
     * 非法的数据包
     *
     * @param ctx {@link ChannelHandlerContext}
     */
    private void invalidMessageHandle(ChannelHandlerContext ctx) {
        ctx.close();
    }
}
