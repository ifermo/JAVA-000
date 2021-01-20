package pers.qingxuan.fastmq.client.handler;

import pers.qingxuann.fastmq.codec.*;
import pers.qingxuann.fastmq.protocol.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> consumer client ByteToMessageCodec
 *
 * @author : QingXuan
 * @since Created in 下午9:48 2021/1/19
 */
public class ConsumerByte2MessageCodec extends ClientByte2MessageCodec {
    private final Map<Byte, MessageCodec> decoders = new HashMap<>();
    private final Map<Class<?>, MessageCodec> encoders = new HashMap<>();

    public ConsumerByte2MessageCodec() {
        HeartbeatCodec heartbeatCodec = new HeartbeatCodec();
        decoders.put(heartbeatCodec.type(), heartbeatCodec);
        encoders.put(Heartbeat.class, heartbeatCodec);

        LoginResponseCodec loginResponseCodec = new LoginResponseCodec();
        decoders.put(loginResponseCodec.type(), loginResponseCodec);
        encoders.put(LoginRequest.class, new LoginRequestCodec());

        PollMessageCodec pollMessageCodec = new PollMessageCodec();
        decoders.put(pollMessageCodec.type(), pollMessageCodec);
        encoders.put(PollRequest.class, new PollRequestCodec());

        OffsetResponseCodec offsetResponseCodec = new OffsetResponseCodec();
        decoders.put(offsetResponseCodec.type(), offsetResponseCodec);
        encoders.put(OffsetRequest.class, new OffsetRequestCodec());
    }

    @Override
    public Map<Byte, MessageCodec> decoders() {
        return decoders;
    }

    @Override
    public Map<Class<?>, MessageCodec> encoders() {
        return encoders;
    }

}
