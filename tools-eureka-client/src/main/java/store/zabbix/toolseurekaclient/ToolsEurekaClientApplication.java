package store.zabbix.toolseurekaclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ToolsEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsEurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    private int port;

    @GetMapping("test")
    public String showPort(@RequestParam(value = "name",required = false) String name){
        if (name == null)
            name = "Anonymous";
        return "my port is "+port +" -> my name is"+name;
    }

}

