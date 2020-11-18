package pers.qingxuan.beans;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p> 注解配置@Bean 装配
 *
 * @author : QingXuan
 * @since Created in 下午10:21 2020/11/16
 */
public class AnnoConfBeanLoadTest {
    public static final Logger log = LoggerFactory.getLogger(AnnoConfBeanLoadTest.class);

    @Test
    public void testOfType() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        Person person = context.getBean(Person.class);
        log.info(person.toString());
    }

    @Test
    public void testOfName() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        Person person = (Person) context.getBean("person");
        log.info(person.toString());
    }
}
