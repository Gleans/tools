package store.zabbix.common.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Fiction.TABLE_NAME)
public class Fiction {
    static final String TABLE_NAME = "fiction";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;//标题
    private String cover; //封面
    private String intro;//简介
    private Integer level; //等级
    private Timestamp uptime;//更新时间
    private Timestamp ctime; //添加时间
    private Integer clicks; //点击数
    private Integer type;//类型
    private Integer status; //状态
    private String author; //作者
    private Double size; //大小
    private String downUrl;//下载路径
}