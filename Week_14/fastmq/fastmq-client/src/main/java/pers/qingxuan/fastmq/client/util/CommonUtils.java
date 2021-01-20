package pers.qingxuan.fastmq.client.util;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * <p> common utils
 *
 * @author : Ray.fuxudong
 * @since Created in 15:02 2021/1/20
 */
public class CommonUtils {

    public static SocketAddress parseAddress(String hostname) {
        String[] strs = hostname.split(":");
        if (strs.length != 2) {
            throw new IllegalArgumentException("brokerUrl pattern error!");
        }
        return new InetSocketAddress(strs[0], Integer.parseInt(strs[1]));
    }
}
