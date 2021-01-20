package pers.qingxuan.fastmq.client.common;

/**
 * <p>
 *
 * @author : Ray.fuxudong
 * @since Created in 14:27 2021/1/20
 */
public class FastmqException extends RuntimeException{
    public FastmqException() {
        super();
    }

    public FastmqException(String message) {
        super(message);
    }

    public FastmqException(String message, Throwable cause) {
        super(message, cause);
    }

    public FastmqException(Throwable cause) {
        super(cause);
    }

    protected FastmqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
