package pers.qingxuan.beans;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.qingxuan.beans.instancefactory.Flower;

/**
 * <p> 借助实例工厂创建Bean
 *
 * @author : QingXuan
 * @since Created in 下午11:07 2020/11/16
 */
public class InstanceFactoryBeanTest {
    public static final Logger log = LoggerFactory.getLogger(InstanceFactoryBeanTest.class);

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("instance-factory.xml");
        Flower car = ctx.getBean(Flower.class);
        log.info(car.toString());
    }
}
