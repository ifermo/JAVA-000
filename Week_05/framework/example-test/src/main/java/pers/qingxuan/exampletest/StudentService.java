package pers.qingxuan.exampletest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qingxuan.examplestarter.prop.Student;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:42 2020/11/17
 */
@Component
public class StudentService {
    @Autowired(required = false)
    private Student student;

    @PostConstruct
    public void test(){
        System.out.println(student);
    }
}
