package pers.qingxuan.redis.json;


import java.lang.reflect.Type;

/**
 * <p> json 序列化和反序列化
 *
 * @author : Ray.fuxudong
 * @since Created in 14:45 2020/11/13
 */
public interface JsonSupport {
    /**
     * 序列化
     *
     * @param msg obj
     * @return json串
     */
    String toJson(Object msg);

    /**
     * 反序列化
     *
     * @param json     json串
     * @param classOfT Class
     * @return obj
     */
    <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException;

    /**
     * 反序列化
     *
     * @param json    json串
     * @param typeOfT {@link Type}
     * @return obj
     */
    <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException;
}
