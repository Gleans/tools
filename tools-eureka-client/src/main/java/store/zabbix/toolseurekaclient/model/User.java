package store.zabbix.toolseurekaclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String email;

    private String username;

    private String password;

    /**
     * 设置true启用用户
     */
    private String enable;

    /**
     * 设置true用户账户未过期
     */
    private String account;

    /**
     * 设置true凭证尚未过期
     */
    private String credentials;

    /**
     * 设置true账户未锁定
     */
    private String locked;

    private String createTime;

    private String lastTime;

    private String salt;

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.locked = user.getLocked();
        this.enable = user.getEnable();
        this.account = user.getAccount();
        this.credentials = user.getCredentials();
    }
    /**
     * 注册时可使用该构造函数初始化数值
     *
     * @param email
     * @param password
     */
/*    public User(String email, String password) {
        this.username = email;
        this.email = email;
        this.password = password;
        this.account = Boolean.TRUE.toString();
        this.credentials = Boolean.TRUE.toString();
        this.locked = Boolean.TRUE.toString();
        this.createTime = new Date();
    }*/

}