package com.github.luca168.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.luca168.auth.domain.UserDO;
import com.github.luca168.auth.mapper.UserMapper;
import com.github.luca168.auth.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserDetailsService, UserService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserDO> userLqw = Wrappers.<UserDO>lambdaQuery()
                .eq(UserDO::getUsername, username);

        UserDO userDO = baseMapper.selectOne(userLqw);
        if (Objects.isNull(userDO)) {
            throw new RuntimeException("User not found");
        }

        return userDO;
    }
}
