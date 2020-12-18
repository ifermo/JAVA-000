package pers.qingxuan.rpcfx.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import pers.qingxuan.rpcfx.core.api.RpcfxResolver;

public class ServiceResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> T resolve(Class<T> clazz) {
        return this.applicationContext.getBean(clazz);
    }
}
