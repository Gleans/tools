package store.zabbix.toolsrouting.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.zabbix.toolsrouting.HystrixClientFallback;

//@FeignClient(value = "eureka-client",fallback = HystrixClientFallback.class)
public interface CallServiceTest {

//    @GetMapping("test")
//    String api(@RequestParam(value = "name",required = false) String name);

}
