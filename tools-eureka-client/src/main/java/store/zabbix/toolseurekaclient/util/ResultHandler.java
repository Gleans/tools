package store.zabbix.toolseurekaclient.util;

import org.apache.ibatis.session.ResultContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Author-Date： cuifuan   2018-06-13 上午 10:51
 * Description：
 */
public class ResultHandler implements org.apache.ibatis.session.ResultHandler{
    @SuppressWarnings("rawtypes")
    private final Map mappedResults = new HashMap();

    @SuppressWarnings("unchecked")
    @Override
    public void handleResult(ResultContext context) {
        @SuppressWarnings("rawtypes")
        Map map = (Map) context.getResultObject();
        Object value=map.get("value");
        if(value!=null)
            mappedResults.put(value,map.get("key").toString()); // xml配置里面的property的值，对应的列
    }
    /**
     * rawtypes:在类params上使用泛型时，禁止相对于非特定类型的警告
     *
     * @Param []
     * @return java.util.Map
     * @Author cfa  2018-07-21
     **/
    @SuppressWarnings("rawtypes")
    public Map getMappedResults() {
        return mappedResults;
    }
}
