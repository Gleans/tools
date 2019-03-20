package store.zabbix.toolseurekaclient.controller;

import com.github.pagehelper.PageInfo;
import store.zabbix.toolseurekaclient.beans.PageResultBean;
import store.zabbix.toolseurekaclient.model.Area;
import store.zabbix.toolseurekaclient.service.AreaService;
import store.zabbix.toolseurekaclient.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AreaController {
    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @RequestMapping("/getArea")
    public PageResultBean<List<Area>> getArea(@RequestBody Map<String,Integer> map){
        PageInfo<Area> pageInfo=new PageInfo<>(areaService.getAreaAll(map));
        PageResultBean<List<Area>> pageResultBean=new PageResultBean<>();
        pageResultBean
                .setData(pageInfo.getList())
                .setTotalRecord(pageInfo.getTotal())
                .setPageNo(pageInfo.getPageNum())
                .setPageSize(pageInfo.getPageSize())
                .setPageCount(pageInfo.getPages());
        return pageResultBean;
    }

    @RequestMapping("/getLocation")
    public Object getLocation() throws Exception {
        String url="https://apis.map.qq.com/tools/geolocation?key=3PQBZ-BOBKD-GDQ4U-PLGJH-RFQCS-HABRO&referer=http://map.qq.com/api/js?v=2.exp";
        return HttpClientUtils.getRequest(url);
    }

}
