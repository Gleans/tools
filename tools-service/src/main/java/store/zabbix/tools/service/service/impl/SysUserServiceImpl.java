package store.zabbix.tools.service.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import store.zabbix.tools.common.entity.SysUser;
import store.zabbix.tools.service.dao.SysUserMapper;
import store.zabbix.tools.service.service.SysUserService;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

}
