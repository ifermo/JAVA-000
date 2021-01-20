package pers.qingxuann.fastmq.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;

/**
 * <p> 数据包类型
 *
 * @author : QingXuan
 * @since Created in 下午12:42 2021/1/17
 */
public final class Constant {
    /**
     * 默认时区
     */
    public static final ZoneOffset ZONE_OFFSET = ZoneOffset.of("Asia/Shanghai");
    /**
     * 默认字符集
     */
    public static final Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * 魔数，数据包必须以改字符开始否则视为非法数据
     */
    public static final byte MAGIC_NUMBER = 0x40;
    /**
     * 心跳数据帧
     */
    public static final byte HEARTBEAT = 0x01;
    /**
     * 登录请求帧
     */
    public static final byte LOGIN_REQUEST = 0x02;
    /**
     * 登录响应帧
     */
    public static final byte LOGIN_RESPONSE = 0x02;
    /**
     * 生产者发送消息请求帧
     */
    public static final byte SEND_REQUEST = 0x03;
    /**
     * 生产者发送消息响应帧
     */
    public static final byte SEND_RESPONSE = 0x04;
    /**
     * 消费者拉取消息请求帧
     */
    public static final byte POLL_REQUEST = 0x05;
    /**
     * 消费者拉取消息响应帧
     */
    public static final byte POLL_RESPONSE = 0x06;
    /**
     * 位移提交帧
     */
    public static final byte OFFSET_COMMIT = 0x07;
}
