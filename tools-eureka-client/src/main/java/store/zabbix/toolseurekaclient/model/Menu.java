package store.zabbix.toolseurekaclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer id;

    private String url;

    private String path;

    private String name;

    private String component;

    private Boolean keepalive;

    private Boolean key;

    private Boolean requireauth;

    private Integer parentid;

    private String iconcls;

    private List<Menu> children;

}