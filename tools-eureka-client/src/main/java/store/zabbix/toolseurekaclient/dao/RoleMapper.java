package store.zabbix.toolseurekaclient.dao;

import store.zabbix.toolseurekaclient.model.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);
    /**
     * 获取全部的权限
     * created by cfa  2018-08-24 下午 8:16
     **/
    List<Role> getRoleAll();

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRolesByUid(Integer id);
}