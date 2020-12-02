package pers.qingxuan.wrsv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:54 2020/11/30
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WrsV2Application {

    public static void main(String[] args) {
        SpringApplication.run(WrsV2Application.class, args);
    }

}
