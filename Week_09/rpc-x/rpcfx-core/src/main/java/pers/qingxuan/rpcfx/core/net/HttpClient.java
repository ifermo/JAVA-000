package pers.qingxuan.rpcfx.core.net;

/**
 * <p> HttpClient
 *
 * @author : QingXuan
 * @since Created in 下午11:32 2020/12/17
 */
public interface HttpClient {
    String call(String url, Object param);
}
