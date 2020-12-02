package pers.qingxuan.wrsv1.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static pers.qingxuan.wrsv1.consts.Constant.*;

/**
 * <p> JdbcTemplate配置
 *
 * @author : QingXuan
 * @since Created in 下午8:01 2020/12/1
 */
@Configuration
public class JdbcTemplateConfig {

    @Bean(name = PRIMARY_JDBC)
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier(PRIMARY_SOURCE) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = RETINUE_JDBC)
    public JdbcTemplate retinueJdbcTemplate(
            @Qualifier(RETINUE_SOURCE) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
