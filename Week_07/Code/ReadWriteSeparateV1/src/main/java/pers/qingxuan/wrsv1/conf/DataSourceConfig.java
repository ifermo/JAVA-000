package pers.qingxuan.wrsv1.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

import static pers.qingxuan.wrsv1.consts.Constant.PRIMARY_SOURCE;
import static pers.qingxuan.wrsv1.consts.Constant.RETINUE_SOURCE;

/**
 * <p> 多数据源配置
 *
 * @author : QingXuan
 * @since Created in 下午7:57 2020/12/1
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = PRIMARY_SOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = RETINUE_SOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.retinue")
    public DataSource retinueDataSource() {
        return DataSourceBuilder.create().build();
    }
}
