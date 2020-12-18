package pers.qingxuan.dubbo;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> provider application
 *
 * @author : QingXuan
 * @since Created in 下午5:16 2020/12/14
 */
@DubboComponentScan(basePackages = "pers.qingxuan.dubbo.service")
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }
}
