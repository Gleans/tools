package store.zabbix.toolsrouting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.zabbix.toolsrouting.service.CallServiceTest;

@RestController
public class TestController {

//    private final CallServiceTest callServiceTest;
//
//    @Autowired
//    public TestController(CallServiceTest callServiceTest) {
//        this.callServiceTest = callServiceTest;
//    }
//
//    @GetMapping("test")
//    public String testCall(@RequestParam(value = "name",required = false) String name){
//        return callServiceTest.api(name);
//    }
}
