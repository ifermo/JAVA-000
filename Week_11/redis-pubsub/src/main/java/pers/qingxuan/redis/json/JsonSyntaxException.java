package pers.qingxuan.redis.json;

/**
 * <p> 自定义json异常
 *
 * @author : Ray.fuxudong
 * @since Created in 14:53 2020/11/13
 */
public class JsonSyntaxException extends RuntimeException {
    public JsonSyntaxException() {
        super();
    }

    public JsonSyntaxException(String message) {
        super(message);
    }

    public JsonSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonSyntaxException(Throwable cause) {
        super(cause);
    }

    protected JsonSyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
