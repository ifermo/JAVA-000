package pers.qingxuan.fastmq.client.common;

/**
 * <p>
 *
 * @author : Ray.fuxudong
 * @since Created in 14:14 2021/1/20
 */
public class ConfigException extends RuntimeException{
    public ConfigException() {
        super();
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigException(Throwable cause) {
        super(cause);
    }

    protected ConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
