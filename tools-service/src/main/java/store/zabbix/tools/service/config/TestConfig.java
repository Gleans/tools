package store.zabbix.tools.service.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "student")
@Data
@ToString
public class TestConfig {
    private String name;
    private int age;
    private String sex;
}
