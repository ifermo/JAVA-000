package pers.qingxuan.rpcfx.core.net;

import pers.qingxuan.rpcfx.core.api.RpcfxException;

import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

/**
 * <p> 结果寄存
 *
 * @author : QingXuan
 * @since Created in 下午8:37 2020/12/17
 */
public class ResultDeposit implements Consumer<String> {
    private final Thread caller;

    private volatile String value;

    public ResultDeposit() {
        this.caller = Thread.currentThread();
    }

    @Override
    public void accept(String res) {
        this.value = res;
        LockSupport.unpark(caller);
    }

    public String content() throws RpcfxException {
        if (value == null) {
            throw new RpcfxException("remote call timeout!");
        }
        return value;
    }
}
