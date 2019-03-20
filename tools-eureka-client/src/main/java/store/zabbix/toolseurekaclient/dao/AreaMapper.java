package store.zabbix.toolseurekaclient.dao;

import store.zabbix.toolseurekaclient.model.Area;

import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    /**
     * 查询全部地区
     * created by cfa  2018-08-06 上午 10:59
     **/
    List<Area> getAreaAll();
}