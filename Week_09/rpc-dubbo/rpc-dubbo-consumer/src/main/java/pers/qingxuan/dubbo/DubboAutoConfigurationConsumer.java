package pers.qingxuan.dubbo;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.qingxuan.doubbo.api.DemoService;

/**
 * <p> dubbo 配置
 *
 * @author : QingXuan
 * @since Created in 下午5:19 2020/12/14
 */
@Configuration
public class DubboAutoConfigurationConsumer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private DemoService demoService;

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info(demoService.sayHello("world"));
        };
    }
}