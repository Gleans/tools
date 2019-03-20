package store.zabbix.toolseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ToolsEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsEurekaApplication.class, args);
	}

}