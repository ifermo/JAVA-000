package pers.qingxuan.beans.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> bean 配置类
 *
 * @author : QingXuan
 * @since Created in 下午10:53 2020/11/16
 */
@Configuration
public class FactoryConfiguration {

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
