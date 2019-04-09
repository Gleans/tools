/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package store.zabbix.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import store.zabbix.common.api.dto.UserDTO;
import store.zabbix.common.api.entity.SysUser;
import store.zabbix.admin.service.SysUserService;
import store.zabbix.common.core.util.ResultBean;
import store.zabbix.common.log.annotation.SysLog;
import store.zabbix.common.security.annotation.Inner;
import store.zabbix.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SysUserService userService;

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping(value = {"/info"})
	public ResultBean info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
			.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new ResultBean<>(Boolean.FALSE, "获取当前用户信息失败");
		}
		return new ResultBean<>(userService.getUserInfo(user));
	}

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	public ResultBean info(@PathVariable String username) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
			.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new ResultBean<>(Boolean.FALSE, String.format("用户信息为空 %s", username));
		}
		return new ResultBean<>(userService.getUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public ResultBean user(@PathVariable Integer id) {
		return new ResultBean<>(userService.getUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/details/{username}")
	public ResultBean user(@PathVariable String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		return new ResultBean<>(userService.getOne(new QueryWrapper<>(condition)));
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return ResultBean
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	public ResultBean userDel(@PathVariable Integer id) {
		SysUser sysUser = userService.getById(id);
		return new ResultBean<>(userService.removeUserById(sysUser));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public ResultBean user(@RequestBody UserDTO userDto) {
		return new ResultBean<>(userService.saveUser(userDto));
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return ResultBean
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public ResultBean updateUser(@Valid @RequestBody UserDTO userDto) {
		return new ResultBean<>(userService.updateUser(userDto));
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public ResultBean getUserPage(Page page, UserDTO userDTO) {
		return new ResultBean<>(userService.getUserWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@SysLog("修改个人信息")
	@PutMapping("/edit")
	public ResultBean updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto);
	}

	/**
	 * @param username 用户名称
	 * @return 上级部门用户列表
	 */
	@GetMapping("/ancestor/{username}")
	public ResultBean listAncestorUsers(@PathVariable String username) {
		return new ResultBean<>(userService.listAncestorUsersByUsername(username));
	}
}
