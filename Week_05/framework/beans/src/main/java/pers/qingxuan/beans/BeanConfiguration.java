package pers.qingxuan.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> 注解配置
 *
 * @author : QingXuan
 * @since Created in 下午10:18 2020/11/16
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public Person person() {
        Person person = new Person();
        person.setName("青玄");
        person.setAge(19);
        return person;
    }
}
