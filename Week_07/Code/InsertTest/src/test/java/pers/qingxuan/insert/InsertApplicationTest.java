package pers.qingxuan.insert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午11:18 2020/11/30
 */
@SpringBootTest
public class InsertApplicationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void test(){
        jdbcTemplate.execute("INSERT INTO order_form(`order_id`,`user_id`,`addr_id`,`total_price`,`state`) VALUES (1,1,1,10.00,1)");
    }
}
