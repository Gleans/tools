package store.zabbix.common.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Timestamp getUptime() {
        return uptime;
    }

    public void setUptime(Timestamp uptime) {
        this.uptime = uptime;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    @Override
    public String toString() {
        return "Fiction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", intro='" + intro + '\'' +
                ", level=" + level +
                ", uptime=" + uptime +
                ", ctime=" + ctime +
                ", clicks=" + clicks +
                ", type=" + type +
                ", status=" + status +
                ", author='" + author + '\'' +
                ", size=" + size +
                ", downUrl='" + downUrl + '\'' +
                '}';
    }
}
