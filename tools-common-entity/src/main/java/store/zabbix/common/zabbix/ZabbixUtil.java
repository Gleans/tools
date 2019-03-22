package store.zabbix.common.zabbix;


import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * com.grandage.zabbix
 *
 * @author yys
 * @date 2018/04/02 13:56
 */
@Service
@Data
public class ZabbixUtil {

    private ZabbixApi zabbixApi;
    private String dbName;

    public boolean init() {
        try {
            zabbixApi = new DefaultZabbixApi("http://ip/zabbix/api_jsonrpc.php");
            zabbixApi.init();
            boolean zabbixLogin = zabbixApi.login("Admin", "zabbix");
            if (!zabbixLogin) {
                zabbixApi = null;
            }
            dbName = "47数据源";
            return zabbixLogin;
        } catch (Exception e) {
            zabbixApi = null;
            return false;
        }

    }


}

