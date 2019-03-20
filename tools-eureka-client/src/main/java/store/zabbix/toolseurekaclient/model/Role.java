package store.zabbix.toolseurekaclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 
 *
 * @Param 
 * @return 
 * @Author cfa  2018-07-31 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private Integer id;

    private String type;

    private String description;
}