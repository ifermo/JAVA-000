package pers.qingxuan.rpcfx.core.buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import pers.qingxuan.rpcfx.core.client.RpcProxyProvider;

/**
 * <p> byte-buddy Proxy
 *
 * @author : QingXuan
 * @since Created in 上午11:26 2020/12/18
 */
public class BuddyProxy implements RpcProxyProvider {

    private final NamingStrategy.AbstractBase namingStrategy;

    public BuddyProxy() {
        this.namingStrategy = new RpcNamingStrategy();
    }

    public <T> T create(final Class<T> serviceClass, final String url) throws Exception {
        BuddyDelegate.setUrl(url);
        return new ByteBuddy()
                .with(namingStrategy)
                .subclass(serviceClass)
                .method(ElementMatchers.isDeclaredBy(serviceClass))
                .intercept(MethodDelegation.to(BuddyDelegate.class))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }
}
