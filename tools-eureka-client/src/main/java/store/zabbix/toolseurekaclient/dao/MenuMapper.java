package store.zabbix.toolseurekaclient.dao;

import store.zabbix.toolseurekaclient.model.Menu;

import java.util.List;

/**
 * Created by sang on 2017/12/28.
 */
public interface MenuMapper {
    /**
     * 查询菜单
     * created by cfa  2018-08-10 上午 12:26
     **/
    List<Menu> queryHasMenu(Integer[] ids);
    /**
     * 查询各级导航
     * created by cfa  2018-08-23 下午 2:14
     **/
    List<Menu> getMenuByParentid(Integer parentid);
    /*
     * description : 更新Menu
     * @return java.lang.Integer
     **/
    Integer updateByPrimaryKeySelective(Menu menu);
}
