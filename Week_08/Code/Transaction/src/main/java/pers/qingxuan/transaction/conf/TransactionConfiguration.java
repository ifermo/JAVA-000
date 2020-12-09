package pers.qingxuan.transaction.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;

/**
 * <p> 配置
 *
 * @author : QingXuan
 * @since Created in 下午10:14 2020/12/9
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {
    /**
     * Create platform transaction manager bean.
     *
     * @param dataSource data source
     * @return platform transaction manager
     */
    @Bean
    public PlatformTransactionManager txManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Create JDBC template bean.
     *
     * @param dataSource data source
     * @return JDBC template bean
     */
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}