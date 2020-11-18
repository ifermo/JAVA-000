package pers.qingxuan.beans;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p> 简单xml <bean/> 装配
 *
 * @author : QingXuan
 * @since Created in 下午10:21 2020/11/16
 */
public class XmlBeanLoadTest {
    public static final Logger log = LoggerFactory.getLogger(XmlBeanLoadTest.class);

    @Test
    public void testOfType() {
        ApplicationContext context = new ClassPathXmlApplicationContext("simple-bean.xml");
        Person person = context.getBean(Person.class);
        log.info(person.toString());
    }

    @Test
    public void testOfName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("simple-bean.xml");
        Person person = (Person) context.getBean("person");
        log.info(person.toString());
    }
}
