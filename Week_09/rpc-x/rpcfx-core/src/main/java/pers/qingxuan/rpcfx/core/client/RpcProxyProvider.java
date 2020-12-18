package pers.qingxuan.rpcfx.core.client;

/**
 * <p> 服务代理 provider
 *
 * @author : QingXuan
 * @since Created in 下午6:15 2020/12/16
 */
public interface RpcProxyProvider {

    <T> T create(final Class<T> serviceClass, final String url) throws Exception;
}
