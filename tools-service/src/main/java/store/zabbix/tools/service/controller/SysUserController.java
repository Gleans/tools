package store.zabbix.tools.service.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.zabbix.tools.common.bean.ResultBean;
import store.zabbix.tools.common.entity.SysUser;
import store.zabbix.tools.service.service.SysUserService;

import javax.validation.Valid;

@Api(tags = "用户模块")
@RestController
@RequestMapping("user")
public class SysUserController {

    private SysUserService sysUserService;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 新增或修改
     */
    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "用户----新增", notes = "传入SysUser")
    public ResultBean<Boolean> submit(@Valid @RequestBody SysUser user) {
        return new ResultBean<>(sysUserService.save(user));
    }

}
