package pers.qingxuan.fastmq.common;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p> 组件生命周期，标注接口无实意
 *
 * @author : Ray.fuxudong
 * @since Created in 14:58 2020/11/2
 */
public interface BeanLifecycle extends InitializingBean, DisposableBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        init();
    }

    /**
     * 在启动的时候执行
     */
    default void init() {
    }

    /**
     * 在销毁的时候执行
     */
    default void destroy() {
    }
}
