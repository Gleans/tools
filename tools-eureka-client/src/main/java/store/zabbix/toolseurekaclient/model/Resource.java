package store.zabbix.toolseurekaclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    private Long id;

    private String url;

    private String name;

    private String method;


}