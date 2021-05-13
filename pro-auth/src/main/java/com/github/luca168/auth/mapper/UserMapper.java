package com.github.luca168.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.github.luca168.auth.domain.UserDO;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserDO> {
}
