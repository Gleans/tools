package store.zabbix.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import store.zabbix.common.security.annotation.EnablePigFeignClients;

/**
 * description:  认证授权中心
 * Created by cuifuan Time: 2019-04-03 15:26
 */
//@EnableFeignClients
//@SpringBootApplication(scanBasePackages = {"store.zabbix"})
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnablePigFeignClients
@SpringCloudApplication
public class ToolsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsAuthApplication.class, args);
    }

}