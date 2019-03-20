package store.zabbix.toolsrouting.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import store.zabbix.common.entity.User;

import java.util.List;

@FeignClient("eureka-client")
public interface UserServiceClients {
    @GetMapping("user")
//@RequestMapping(value = "user",method = RequestMethod.GET)
    List<User> getUserList(@RequestParam("username") String name,@RequestParam("id") Long id);
//这里不加@RequestParam会报异常IllegalStateException: Method has too many Body parameters，造成无法启动
    @PostMapping("user")
    Integer createUser(@RequestBody User user);

    @PutMapping(value = "user",produces = "application/json")
    Integer updateUser(@RequestBody User user);

    @DeleteMapping(value = "user",produces = "application/json")
    Integer deleteUser(@RequestBody List<Integer> ids);
}
