package store.zabbix.toolsrouting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.zabbix.common.entity.User;
import store.zabbix.common.bean.ResultBean;
import store.zabbix.toolsrouting.service.UserServiceClients;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {
    private final UserServiceClients userServiceClients;

    @Autowired
    public UserController(UserServiceClients userServiceClients) {
        this.userServiceClients = userServiceClients;
    }

    @GetMapping
    public ResultBean<List<User>> getUserList(String name, Long id) {
        return new ResultBean<>(userServiceClients.getUserList(name, id));
    }

    @PostMapping
    public ResultBean<Integer> createUser(@RequestBody User user) {
        return new ResultBean<>(userServiceClients.createUser(user));
    }

    @PutMapping
    public ResultBean<Integer> updateUser(@RequestBody User user){
        return new ResultBean<>(userServiceClients.updateUser(user));
    }

    @DeleteMapping
    public ResultBean<Integer> deleteUser(@RequestBody List<Integer> integerList){
        return new ResultBean<>(userServiceClients.deleteUser(integerList));
    }
}
