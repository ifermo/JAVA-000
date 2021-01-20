package pers.qingxuan.fastmq.client.serialization;

/**
 * <p> An interface for converting bytes to objects.
 *
 * @author : Ray.fuxudong
 * @since Created in 10:47 2021/1/20
 */
public interface Deserializer<T> {

    /**
     * Deserialize a record value from a byte array into a value or object.
     * @param topic topic associated with the data
     * @param data serialized bytes; may be null;
     * @return deserialized typed data; may be null
     */
    T deserialize(String topic, byte[] data);
}
