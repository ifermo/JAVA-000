package pers.qingxuann.fastmq.protocol;

/**
 * <p> 客户端登录请求
 *
 * @author : QingXuan
 * @since Created in 下午12:28 2021/1/17
 */
public class LoginRequest implements Message{
    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
