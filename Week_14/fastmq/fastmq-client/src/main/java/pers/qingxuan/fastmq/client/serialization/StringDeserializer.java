package pers.qingxuan.fastmq.client.serialization;

import java.nio.charset.StandardCharsets;

/**
 * <p> Simple string deserializer
 *
 * @author : Ray.fuxudong
 * @since Created in 14:37 2021/1/20
 */
public class StringDeserializer implements Deserializer<String> {

    @Override
    public String deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        return new String(data, StandardCharsets.UTF_8);
    }
}
