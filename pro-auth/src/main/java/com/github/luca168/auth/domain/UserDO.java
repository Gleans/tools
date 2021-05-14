package com.github.luca168.auth.domain;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 登录用户信息
 */
@Data
@TableName("user")
public class UserDO implements Serializable, UserDetails {

    @TableId(value = "user_id", type = IdType.INPUT)
    private String userId;

    private String username;
    private String password;

    private Long createTime;

    private Long updateTime;
    @TableField(exist = false)
    private String token;
    @TableField(exist = false)
    private Set<String> roles;
    @TableField(exist = false)
    private String avatar;

    /**
     * 微信OpenId
     */
    @TableField(exist = false)
    private String wxOpenId;

    /**
     * 用户类型（1.小程序 2.后台登陆）
     */
    @TableField(insertStrategy = FieldStrategy.IGNORED)
    private Integer userType;

    @TableField(exist = false)
    private String sessionKey;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authSet = new HashSet<>();
        if (Objects.nonNull(this.roles)) {
            this.roles.forEach(role -> authSet.add(new SimpleGrantedAuthority(role)));
        }
        return authSet;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /*账号是否没过期*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*账号是否没过期*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*密码是否没过期*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
