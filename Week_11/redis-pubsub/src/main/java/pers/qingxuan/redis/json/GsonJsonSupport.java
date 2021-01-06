package pers.qingxuan.redis.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <p> 基于 Gson 的 JsonSupport 实现
 *
 * @author : Ray.fuxudong
 * @since Created in 14:47 2020/11/13
 */
public class GsonJsonSupport implements JsonSupport {
    private final Gson gson;

    public GsonJsonSupport() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateJsonAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeJsonAdapter())
                .create();
    }

    @Override
    public String toJson(Object msg) {
        return gson.toJson(msg);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public <T> T fromJson(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            throw new JsonSyntaxException(e);
        }
    }

    ///////////////////////////////////////////
    // 时间类的序列化格式
    ///////////////////////////////////////////

    public static class LocalDateTimeJsonAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String datetime = json.getAsJsonPrimitive().getAsString();
            return LocalDateTime.parse(datetime, formatter);
        }

        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(formatter));
        }
    }

    public static class LocalDateJsonAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String datetime = json.getAsJsonPrimitive().getAsString();
            return LocalDate.parse(datetime, formatter);
        }

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(formatter));
        }
    }

    public static class LocalTimeJsonAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        @Override
        public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String datetime = json.getAsJsonPrimitive().getAsString();
            return LocalTime.parse(datetime, formatter);
        }

        @Override
        public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(formatter));
        }
    }

}
