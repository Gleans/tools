package store.zabbix.toolseurekaclient.controller;

import com.github.pagehelper.PageInfo;
import store.zabbix.toolseurekaclient.model.Menu;
import store.zabbix.toolseurekaclient.model.User;
import store.zabbix.toolseurekaclient.service.AreaService;
import store.zabbix.toolseurekaclient.service.MenuService;
import store.zabbix.toolseurekaclient.service.UserService;
import store.zabbix.toolseurekaclient.beans.ResultBean;
import store.zabbix.toolseurekaclient.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转控制器
 */
@RestController
public class UserController {
    private final UserService userService;
    private final AreaService areaService;
    @Autowired
    public MenuService menuService;


    @Autowired
    public UserController(UserService userService, AreaService areaService) {
        this.userService = userService;
        this.areaService = areaService;
    }

    /**
     * 获取当前用户名
     * created by cfa  2018-08-20 上午 10:28
     **/
    @GetMapping(value = "/currentUserName")
    public String currentUserName() {
        return UserUtils.getCurrentUser().getUsername();
    }

    /**
     * 动态路由
     * created by cfa  2018-08-20 上午 10:47
     **/
    @RequestMapping("/router")
    public ResultBean router() {
        return new ResultBean<>(menuService.queryHasMenu());
    }

    /**
     * 注册用户, 返回新对象的id
     *
     * @param map
     * @return
     */
    @PostMapping("/reguser")
    public ResultBean<Integer> add(@RequestBody Map map) {
        return new ResultBean<>(userService.insertUser(map));
    }



    /*-----------------------------以下是测试的用例----------------------------------*/


    @RequestMapping("/getNav")
    public List<Menu> getNav() {
        return menuService.queryHasMenu();
    }


    @RequestMapping("/logoutsuccess")
    public String logoutsuccess() {
        return "注销成功";
    }


    @RequestMapping(value = {"/404", "/Access_Denied"})
    public String errPage(HttpServletRequest request) {
        return "404";
    }

    /**
     * 更改密码页
     *
     * @return
     */
    @RequestMapping(value = "/changePasswordPage", method = RequestMethod.GET)
    public String changePasswordPage() {
        return "changePasswordPage";
    }
    /*
     * description : 返回用户列表
     * @return store.zabbix.toolseurekaclient.beans.ResultBean<com.github.pagehelper.PageInfo<store.zabbix.toolseurekaclient.model.User>>
     **/
    @GetMapping("user")
    public ResultBean<PageInfo<User>> getUser(PageInfo pageInfo){
        return new ResultBean<>(userService.getUserList(pageInfo));
    }
}
