package pers.qingxuan.rpcfx.core.client;

import pers.qingxuan.rpcfx.core.buddy.BuddyProxy;

public final class Rpcfx {
    private static final RpcProxyProvider proxyProvider;

    static {
        proxyProvider = new BuddyProxy();
    }

    public static <T> T create(final Class<T> serviceClass, final String url) throws Exception {
        return proxyProvider.create(serviceClass, url);

    }
}
