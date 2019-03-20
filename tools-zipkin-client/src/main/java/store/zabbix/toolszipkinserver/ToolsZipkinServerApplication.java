package store.zabbix.toolszipkinserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ToolsZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsZipkinServerApplication.class, args);
    }

    @Value("${server.port}")
    private int port;

    @GetMapping("/")
    public Object welcome() {
        return "hello my port is" + port;
    }

}