package store.zabbix.toolseurekaclient.controller;

import store.zabbix.toolseurekaclient.configuration.RequestMappingHandlerConfig;
import store.zabbix.toolseurekaclient.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * 调用API
 * created by cfa  2018-08-23 下午 3:09
 **/
@RestController
public class ApiClient {
    private static final String news = "https://www.apiopen.top/journalismApi";
    @Autowired
    RequestMappingHandlerConfig requestMappingHandlerConfig;
    @GetMapping("/news")
    public Object news() {
        return HttpClientUtils.getRequest(news);
    }
    @RequestMapping(value = "/hello",name = "获取URL")
    public List<Map> detectHandlerMethods() {
        final RequestMappingHandlerMapping requestMappingHandlerMapping = requestMappingHandlerConfig.requestMappingHandlerMapping();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        StringBuilder stringBuilder=new StringBuilder();
        List<Map> mapList=new ArrayList<>();
        Set<RequestMappingInfo> mappings = map.keySet();
        Map<String, String> reversedMap;
        for (RequestMappingInfo info : mappings) {
            reversedMap = new HashMap<>();
//            HandlerMethod method = map.get(info);
            reversedMap.put("url",info.getPatternsCondition().getPatterns().toString());
            reversedMap.put("name",info.getName());
            reversedMap.put("method",info.getMethodsCondition().getMethods().toString());
            mapList.add(reversedMap);
//            stringBuilder.append(method.toString() + "=====>" + info.getPatternsCondition().toString());
        }
        return mapList;
    }
}
