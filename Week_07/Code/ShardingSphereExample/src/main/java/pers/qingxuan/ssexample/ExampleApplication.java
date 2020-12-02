package pers.qingxuan.ssexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:54 2020/11/30
 */
@SpringBootApplication()
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}
