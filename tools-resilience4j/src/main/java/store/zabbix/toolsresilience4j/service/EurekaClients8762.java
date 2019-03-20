package store.zabbix.toolsresilience4j.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import store.zabbix.common.entity.User;

import java.util.List;

/**
 * store.zabbix.toolsresilience4j.service
 *
 * @author cuifuan
 * @date 2019/03/08 12:07
 */


@FeignClient("eureka-client")
public interface EurekaClients8762 {

    @GetMapping("user")
    List<User> getUser();
}
