package store.zabbix.toolseurekaclient.controller;

import store.zabbix.toolseurekaclient.model.Menu;
import store.zabbix.toolseurekaclient.service.MenuService;
import store.zabbix.toolseurekaclient.beans.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单的控制器
 * created by cfa  2018-08-23 下午 2:17
 * 4.0往上
 * RestController 使当前类返回的都是json格式，省去各方法前的ResponseBosy注解
 **/
@RestController
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    /*
     * description : 返回带上泛型，清楚识别的返回类型
     * @return store.zabbix.toolseurekaclient.beans.ResultBean<java.util.List<store.zabbix.toolseurekaclient.model.Menu>>
     **/
    @RequestMapping(value = "/menu",method = RequestMethod.GET,name = "Menu菜单查询")
    public ResultBean<List<Menu>> getMenu(@RequestParam(value = "id", defaultValue = "-1") Integer id) {
        return new ResultBean<>(menuService.getMenuByParentid(id));
    }

    @RequestMapping(value = "/menu",method = RequestMethod.PUT)
    public ResultBean<Integer> getMenu(@RequestBody Menu menu) {
        return new ResultBean<>(menuService.updateByPrimaryKeySelective(menu));
    }


}
