package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import pers.qingxuann.fastmq.protocol.LoginRequest;
import pers.qingxuann.fastmq.protocol.Message;

import java.nio.charset.StandardCharsets;

import static pers.qingxuann.fastmq.common.Constant.LOGIN_REQUEST;

/**
 * <p> LoginRequest codec
 *
 * @author : QingXuan
 * @since Created in 下午3:09 2021/1/17
 */
public class LoginRequestCodec implements MessageCodec{
    @Override
    public byte type() {
        return LOGIN_REQUEST;
    }

    @Override
    public Message read(ByteBuf buf) {
        LoginRequest result = new LoginRequest();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        result.setClientId(new String(bytes, StandardCharsets.UTF_8));
        return result;
    }

    @Override
    public ByteBuf write(Message obj) {
        LoginRequest request = (LoginRequest) obj;
        ByteBuf buf = buffer();
        byte[] bytes = request.getClientId().getBytes(StandardCharsets.UTF_8);
        buf.writeBytes(bytes);
        return buf;
    }
}
