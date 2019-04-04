package store.zabbix.common.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * com.example.demo
 *
 * @author yys
 * @date 2018/07/04 10:38
 */
@Configuration  //标识该类被纳入spring容器中实例化并管理
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource") //加载时读取指定的配置信息,前缀为spring.datasource
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}