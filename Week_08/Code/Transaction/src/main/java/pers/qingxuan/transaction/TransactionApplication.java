package pers.qingxuan.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pers.qingxuan.transaction.conf.TransactionConfiguration;

/**
 * <p> app
 *
 * @author : QingXuan
 * @since Created in 下午10:13 2020/12/9
 */
@SpringBootApplication
@Import(TransactionConfiguration.class)
public class TransactionApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class);
    }
}
