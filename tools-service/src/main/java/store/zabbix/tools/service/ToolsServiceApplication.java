package store.zabbix.tools.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import store.zabbix.tools.service.config.TestConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({TestConfig.class})
public class ToolsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsServiceApplication.class, args);
    }

}
