package pers.qingxuan.beans;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.qingxuan.beans.factory.Color;
import pers.qingxuan.beans.factory.FactoryConfiguration;

/**
 * <p> 借助FactoryBean创建Bean
 *
 * @author : QingXuan
 * @since Created in 下午10:55 2020/11/16
 */
public class FactoryBeanLoadTest {
    public static final Logger log = LoggerFactory.getLogger(FactoryBeanLoadTest.class);

    @Test
    public void testXml() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("factory-bean.xml");
        Color color = ctx.getBean(Color.class);
        log.info(color.toString());
    }

    @Test
    public void testAnno() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(FactoryConfiguration.class);
        Color color = ctx.getBean(Color.class);
        log.info(color.toString());
    }

}
