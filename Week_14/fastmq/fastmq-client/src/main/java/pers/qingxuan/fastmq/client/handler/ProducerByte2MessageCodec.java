package pers.qingxuan.fastmq.client.handler;

import pers.qingxuann.fastmq.codec.*;
import pers.qingxuann.fastmq.protocol.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> producer client ByteToMessageCodec
 *
 * @author : QingXuan
 * @since Created in 下午9:48 2021/1/19
 */
public class ProducerByte2MessageCodec extends ClientByte2MessageCodec {
    private final Map<Byte, MessageCodec> decoders = new HashMap<>();
    private final Map<Class<?>, MessageCodec> encoders = new HashMap<>();

    public ProducerByte2MessageCodec() {
        HeartbeatCodec heartbeatCodec = new HeartbeatCodec();
        decoders.put(heartbeatCodec.type(), heartbeatCodec);
        encoders.put(Heartbeat.class, heartbeatCodec);

        LoginResponseCodec loginResponseCodec = new LoginResponseCodec();
        decoders.put(loginResponseCodec.type(), loginResponseCodec);
        encoders.put(LoginRequest.class, new LoginRequestCodec());

        OfferResponseCodec offerResponseCodec=new OfferResponseCodec();
        decoders.put(offerResponseCodec.type(), offerResponseCodec);
        encoders.put(OfferMessage.class, new OfferMessageCodec());
    }

    @Override
    public Map<Byte, MessageCodec> decoders() {
        return null;
    }

    @Override
    public Map<Class<?>, MessageCodec> encoders() {
        return null;
    }
}
