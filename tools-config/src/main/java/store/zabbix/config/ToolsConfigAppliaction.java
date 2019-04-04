package store.zabbix.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableConfigServer
@SpringCloudApplication
@RestController
public class ToolsConfigAppliaction {

    public static void main(String[] args) {
        SpringApplication.run(ToolsConfigAppliaction.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World! My name is config server.";
    }

}