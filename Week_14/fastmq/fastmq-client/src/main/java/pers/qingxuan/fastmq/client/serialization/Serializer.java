package pers.qingxuan.fastmq.client.serialization;

/**
 * <p> An interface for converting objects to bytes.
 *
 * @author : Ray.fuxudong
 * @since Created in 10:42 2021/1/20
 */
public interface Serializer<T> {

    /**
     * Convert {@code data} into a byte array.
     *
     * @param topic topic associated with data
     * @param data typed data
     * @return serialized bytes
     */
    byte[] serialize(String topic, T data);

}
