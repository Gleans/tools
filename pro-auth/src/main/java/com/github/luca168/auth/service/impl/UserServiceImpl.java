package com.github.luca168.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.luca168.auth.domain.UserDO;
import com.github.luca168.auth.mapper.UserMapper;
import com.github.luca168.auth.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserDetailsService, UserService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserDO> userLqw = Wrappers.<UserDO>lambdaQuery()
                .eq(UserDO::getUsername, username);
        UserDO userDO = Optional.of(baseMapper.selectOne(userLqw))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

//        GrantedAuthority authority = new SimpleGrantedAuthority(userDO.getRoles());
        return userDO;
    }
}
