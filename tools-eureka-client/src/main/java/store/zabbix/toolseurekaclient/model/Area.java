package store.zabbix.toolseurekaclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area {
    private Integer id;

    private Integer postalcode;

    private String address;

    private Integer type;

}