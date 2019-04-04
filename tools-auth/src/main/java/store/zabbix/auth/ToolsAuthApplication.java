package store.zabbix.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * description:  认证授权中心
 * Created by cuifuan Time: 2019-04-03 15:26
 */
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"store.zabbix"})
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ToolsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsAuthApplication.class, args);
    }

}