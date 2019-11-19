package store.zabbix.tools.gateway.entity;
/*
 * @author chou
 * @Description
 * @since 2019/11/13
 **/

import java.util.LinkedHashMap;
import java.util.Map;

public class PredicateEntity {
    //断言对应的Name
    private String name;

    //断言规则
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
