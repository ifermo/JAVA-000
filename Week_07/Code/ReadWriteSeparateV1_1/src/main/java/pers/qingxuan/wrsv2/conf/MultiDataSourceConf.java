package pers.qingxuan.wrsv2.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import static pers.qingxuan.wrsv2.consts.Constant.PRIMARY_SOURCE;
import static pers.qingxuan.wrsv2.consts.Constant.RETINUE_SOURCE;

/**
 * <p> 多数据源配置
 *
 * @author : QingXuan
 * @since Created in 下午7:57 2020/12/1
 */
@Configuration
public class MultiDataSourceConf {

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

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> map = new HashMap<>();
        map.put(PRIMARY_SOURCE, primaryDataSource());
        map.put(RETINUE_SOURCE, retinueDataSource());

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(map);
        dataSource.setDefaultTargetDataSource(primaryDataSource());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
