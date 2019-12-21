package store.zabbix.tools.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import store.zabbix.tools.common.entity.SysUser;

@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
}