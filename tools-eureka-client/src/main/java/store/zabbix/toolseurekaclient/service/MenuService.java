package store.zabbix.toolseurekaclient.service;

import store.zabbix.toolseurekaclient.model.Menu;

import java.util.List;

/**
 * 菜单接口
 * created by cfa  2018-08-02 下午 3:24
 **/
public interface MenuService {
    /**
     * 查询各级导航
     * created by cfa  2018-08-23 下午 2:14
     **/
    List<Menu> getMenuByParentid(Integer parentid);
    /*
     * description : 获取菜单
     * @return java.util.List<store.zabbix.toolseurekaclient.model.Menu>
     **/
    List<Menu> queryHasMenu();

    /*
     * description : 更新Menu
     * @return java.lang.Integer
     **/
    Integer updateByPrimaryKeySelective(Menu menu);
}
