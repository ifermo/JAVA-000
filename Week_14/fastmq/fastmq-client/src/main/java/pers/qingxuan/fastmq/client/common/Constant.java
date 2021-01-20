package pers.qingxuan.fastmq.client.common;

import io.netty.util.AttributeKey;

/**
 * <p>
 *
 * @author : Ray.fuxudong
 * @since Created in 10:10 2021/1/20
 */
public class Constant {
    /**
     * broker ip
     */
    public static final AttributeKey<String> HOST = AttributeKey.valueOf("host");
    /**
     * broker port
     */
    public static final AttributeKey<Integer> PORT = AttributeKey.valueOf("port");
    /**
     * 连接成功标识，用户判断重连延迟
     */
    public static final AttributeKey<Boolean> SUC = AttributeKey.valueOf("success");
}
