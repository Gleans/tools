package com.github.springtools.proservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class ProServiceApplication {

    @GetMapping("/")
    public String hello() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProServiceApplication.class, args);
    }

}
