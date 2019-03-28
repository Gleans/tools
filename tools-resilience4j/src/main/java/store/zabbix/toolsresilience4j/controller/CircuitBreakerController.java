package store.zabbix.toolsresilience4j.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.zabbix.common.bean.ResultBean;
import store.zabbix.common.jpa.entity.User;
import store.zabbix.toolsresilience4j.service.BusinessService;
import store.zabbix.toolsresilience4j.service.EurekaClients8762;
import store.zabbix.toolsresilience4j.service.SyncRetryService;

import java.util.List;
import java.util.concurrent.CompletionStage;


@RestController
@RequestMapping("/circuit")
public class CircuitBreakerController {
    private final CircuitBreaker circuitBreaker;
    private final BusinessService service;
    private final RateLimiter rateLimiter;
    private final EurekaClients8762 clients;

    @Autowired
    private SyncRetryService retryService;

    public CircuitBreakerController(CircuitBreakerRegistry registry,
                                    BusinessService service,
                                    EurekaClients8762 clients,
                                    RateLimiterRegistry rateLimiterRegistry) {
        this.circuitBreaker = registry.circuitBreaker("backendA");
        this.rateLimiter = rateLimiterRegistry.rateLimiter("limiterA");
        this.service = service;
        this.clients = clients;
    }

    @GetMapping("/func")
    public ResultBean<List<User>> func() {
        return new ResultBean<>(CircuitBreaker.decorateSupplier(circuitBreaker, clients::getUser).get());
    }

    @GetMapping("/aop")
    public String aop(@RequestParam(required = false) String str) {
        return Try.ofSupplier(CircuitBreaker.decorateSupplier(circuitBreaker, () -> service.aop(str)))
                .recover(CircuitBreakerOpenException.class, "断路器已开启")
                .recover(RuntimeException.class, recory()).get();
    }

    private String recory() {
        return "请求失败,回滚操作...";
    }

    @GetMapping("time")
    public String func1() {
        return Try.ofSupplier(RateLimiter.decorateSupplier(rateLimiter, service::func))
                .recover(RequestNotPermitted.class, "Request Not Permitted!!").get();
    }

    @GetMapping("retry")
    public String retry() {
         return service.retryOnException();
    }

    @GetMapping("async")
    public CompletionStage<String> asyncRetryOnResult() {
         return service.asyncRetryOnException().get();
    }



}
