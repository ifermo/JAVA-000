package pers.qingxuan.fastmq.client.serialization;

import java.nio.charset.StandardCharsets;

/**
 * <p> Simple string serializer
 *
 * @author : Ray.fuxudong
 * @since Created in 14:37 2021/1/20
 */
public class StringSerializer implements Serializer<String> {

    @Override
    public byte[] serialize(String topic, String data) {
        if (data == null) {
            return null;
        }
        return data.getBytes(StandardCharsets.UTF_8);
    }
}
