package store.zabbix.common.zabbix;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author yys
 *
 */
public interface ZabbixApi {

	void init();

	JSONObject call(Map request);

	boolean login(String user, String password);
}
