package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import pers.qingxuann.fastmq.protocol.LoginResponse;
import pers.qingxuann.fastmq.protocol.Message;

import static pers.qingxuann.fastmq.common.Constant.LOGIN_RESPONSE;

/**
 * <p> LoginRequest codec
 *
 * @author : QingXuan
 * @since Created in 下午3:09 2021/1/17
 */
public class LoginResponseCodec implements MessageCodec {
    @Override
    public byte type() {
        return LOGIN_RESPONSE;
    }

    @Override
    public Message read(ByteBuf buf) {
        LoginResponse result = new LoginResponse();
        result.setOk(buf.readBoolean());
        return result;
    }

    @Override
    public ByteBuf write(Message obj) {
        LoginResponse response = (LoginResponse) obj;
        ByteBuf buf = buffer();
        buf.writeBoolean(response.isOk());
        return buf;
    }
}
