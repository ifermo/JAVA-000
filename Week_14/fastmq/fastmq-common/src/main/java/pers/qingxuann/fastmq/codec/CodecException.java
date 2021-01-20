package pers.qingxuann.fastmq.codec;

/**
 * <p> encode Letter Exception
 *
 * @author : QingXuan
 * @since Created in 下午1:19 2021/1/17
 */
public class CodecException extends RuntimeException{
    public CodecException() {
        super();
    }

    public CodecException(String message) {
        super(message);
    }

    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodecException(Throwable cause) {
        super(cause);
    }

    protected CodecException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
