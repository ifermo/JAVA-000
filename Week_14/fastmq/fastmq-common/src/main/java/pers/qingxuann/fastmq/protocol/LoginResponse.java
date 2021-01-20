package pers.qingxuann.fastmq.protocol;

/**
 * <p> 登录响应
 *
 * @author : QingXuan
 * @since Created in 下午12:29 2021/1/17
 */
public class LoginResponse implements Message {
    private boolean ok;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
