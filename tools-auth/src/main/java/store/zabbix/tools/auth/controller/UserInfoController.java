package store.zabbix.tools.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @GetMapping("/user/web")
    public String web() {
        return "hello web";
    }

    @GetMapping("/user/android")
    public String android() {
        return "hello android";
    }

}