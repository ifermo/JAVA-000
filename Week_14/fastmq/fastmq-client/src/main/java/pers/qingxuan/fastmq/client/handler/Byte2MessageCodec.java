package pers.qingxuan.fastmq.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import pers.qingxuann.fastmq.codec.*;
import pers.qingxuann.fastmq.protocol.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pers.qingxuann.fastmq.common.Constant.MAGIC_NUMBER;

/**
 * <p> server ByteToMessageDecoder
 *
 * @author : QingXuan
 * @since Created in 下午9:48 2021/1/19
 */
public class Byte2MessageCodec extends ByteToMessageCodec<Message> {
    private final Map<Byte, MessageCodec> decoders = new HashMap<>();
    private final Map<Class<?>, MessageCodec> encoders = new HashMap<>();

    public Byte2MessageCodec() {
        HeartbeatCodec heartbeatCodec = new HeartbeatCodec();
        decoders.put(heartbeatCodec.type(), heartbeatCodec);
        encoders.put(Heartbeat.class, heartbeatCodec);

        LoginRequestCodec loginRequestCodec = new LoginRequestCodec();
        decoders.put(loginRequestCodec.type(), loginRequestCodec);
        encoders.put(LoginRequest.class, loginRequestCodec);

        LoginResponseCodec loginResponseCodec = new LoginResponseCodec();
        decoders.put(loginResponseCodec.type(), loginResponseCodec);
        encoders.put(LoginResponse.class, loginResponseCodec);

        encoders.put(OfferMessage.class, new OfferMessageCodec());
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        MessageCodec encoder = encoders.get(msg.getClass());
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
        MessageCodec decoder = decoders.get(in.readByte());
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
