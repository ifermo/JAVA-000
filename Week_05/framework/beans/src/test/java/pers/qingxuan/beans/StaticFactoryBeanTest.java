package pers.qingxuan.beans;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.qingxuan.beans.staticfactory.Car;

/**
 * <p> 借助静态工厂创建Bean
 *
 * @author : QingXuan
 * @since Created in 下午11:07 2020/11/16
 */
public class StaticFactoryBeanTest {
    public static final Logger log = LoggerFactory.getLogger(StaticFactoryBeanTest.class);

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("static-factory.xml");
        Car car = ctx.getBean(Car.class);
        log.info(car.toString());
    }
}
