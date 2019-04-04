package store.zabbix.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * description:
 * Created by cuifuan Time: 2019-04-02 11:28
 */
@EnableEurekaServer
@SpringBootApplication
public class ToolsEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsEurekaApplication.class, args);
	}

}