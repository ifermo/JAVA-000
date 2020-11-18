package pers.qingxuan.beans;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.qingxuan.beans.bean.BeanScanConfiguration;

import java.util.stream.Stream;

/**
 * <p> 配置扫描方式
 *
 * @author : QingXuan
 * @since Created in 下午10:44 2020/11/16
 */
public class ComponentScanTest {
    public static final Logger log = LoggerFactory.getLogger(ComponentScanTest.class);

    @Test
    public void testConfig() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanScanConfiguration.class);
        Stream.of(ctx.getBeanDefinitionNames()).forEach(log::info);
    }

    @Test
    public void testPackages() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("pers.qingxuan.beans.bean");
        Stream.of(ctx.getBeanDefinitionNames()).forEach(log::info);
    }
}
