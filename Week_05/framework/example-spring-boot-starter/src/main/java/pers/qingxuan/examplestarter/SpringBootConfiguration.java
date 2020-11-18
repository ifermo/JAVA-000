package pers.qingxuan.examplestarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pers.qingxuan.examplestarter.prop.Klass;
import pers.qingxuan.examplestarter.prop.Student;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * <p> spring-boot-starter Configuration
 *
 * @author : QingXuan
 * @since Created in 下午7:47 2020/11/17
 */
@Configuration
@ComponentScan("pers.qingxuan.examplestarter.prop")
@EnableConfigurationProperties(ExampleSpringBootProperties.class)
@ConditionalOnProperty(prefix = "spring.example", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SpringBootConfiguration {
    @Resource
    private ExampleSpringBootProperties properties;

    @Bean
    public Student student() {
        int studentId = properties.getStudentId() == null ? 0 : properties.getStudentId();
        return new Student(studentId, properties.getStudentName());
    }

    @Bean
    public Klass klass(Student student) {
        Klass klass = new Klass();
        klass.students = Collections.singletonList(student);
        return klass;
    }
}
