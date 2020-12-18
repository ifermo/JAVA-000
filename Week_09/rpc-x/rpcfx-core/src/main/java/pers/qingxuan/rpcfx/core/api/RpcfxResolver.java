package pers.qingxuan.rpcfx.core.api;

public interface RpcfxResolver {

    <T> T resolve(Class<T> clazz);

}
