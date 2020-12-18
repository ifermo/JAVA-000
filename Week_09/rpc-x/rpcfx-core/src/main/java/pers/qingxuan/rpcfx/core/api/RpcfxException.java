package pers.qingxuan.rpcfx.core.api;

/**
 * <p> exception
 *
 * @author : QingXuan
 * @since Created in 下午2:16 2020/12/18
 */
public class RpcfxException extends RuntimeException {
    public RpcfxException() {
        super();
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcfxException(Throwable cause) {
        super(cause);
    }

    protected RpcfxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
