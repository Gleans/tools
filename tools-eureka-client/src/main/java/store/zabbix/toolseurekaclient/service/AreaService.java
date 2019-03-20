package store.zabbix.toolseurekaclient.service;

import store.zabbix.toolseurekaclient.model.Area;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface AreaService {
    /**
     * 查询全部地区
     * created by cfa  2018-08-06 上午 10:59
     **/
    List<Area> getAreaAll(Map<String,Integer> map);

    String getAddress(HttpServletRequest request, HttpServletResponse response, int id) throws Exception;
}
