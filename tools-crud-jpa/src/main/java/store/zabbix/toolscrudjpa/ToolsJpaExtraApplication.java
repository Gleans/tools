package store.zabbix.toolscrudjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EntityScan(basePackages = {"store.zabbix.common.entity"})
@EnableJpaRepositories(basePackages = {"store.zabbix.toolscrudjpa"})
@RestController
@SpringCloudApplication
public class ToolsJpaExtraApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ToolsJpaExtraApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(ToolsJpaExtraApplication.class, args);
    }

    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }

    @RequestMapping("/")
    public String index() {
        return "Hello Docker!";
    }

}

//@EntityScan(basePackages = {"store.zabbix.common.entity"})
//@EnableJpaRepositories(basePackages = {"store.zabbix.toolscrudjpa"})
//@RestController
//@SpringBootApplication
//public class ToolsJpaExtraApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(ToolsJpaExtraApplication.class, args);
//    }
//
//    @RequestMapping("/")
//    public String index() {
//        return "Hello Docker!";
//    }
//}