package com.dingfeng.totoro.controller;

import com.dingfeng.totoro.error.ErrorCode;
import com.dingfeng.totoro.error.ReturnValue;
import com.dingfeng.totoro.model.User;
import com.dingfeng.totoro.service.UserService;
import com.dingfeng.totoro.util.CheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @Description: 用户管理控制器
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/13
 */
@Api(tags = "用户管理接口列表", value = "提供用户管理相关接口")
@RestController
@RequestMapping("/oauth/user")
@Slf4j
public class UserController {
	@Autowired
	protected UserService userService;
	@Autowired
	protected PasswordEncoder passwordEncoder;
	@Autowired
	protected ResourceServerTokenServices resourceServerTokenServices;

	/**
	 * 当前用户认证信息
	 * @Param: [principal]
	 * @Author: chendingfengmail@163.com
	 * @Date: 2020/8/13
	 */
	@GetMapping(value = "/me")
    public Principal me(Principal principal) {
        log.info("资源服务获取用户信息：" + principal);
        return principal;
    }


	/**
	 * 添加一个用户
	 * @Param: [current_user, current_user_type, user]
	 * @Author: chendingfengmail@163.com
	 * @Date: 2020/8/13
	 */
	@ApiOperation(value="添加一个新的用户", notes="用户名、密码必填")
	@ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType="body")})
	@PostMapping(value = "/add")
	public ReturnValue<Long> add(@RequestBody User user){
		try {
			// 同名检测
			User temp = userService.findByUserName(user.getUsername());
			if (!CheckUtil.isNull(temp)) {
				return new ReturnValue<Long>(ErrorCode.ERROR_OBJECT_EXIST, "该用户名已存在!");
			}
			// 加密password
			user.setPassword("888888");
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			// 默认值字段赋默认值
			user.setEnabled((byte)1);
			user.setExpired((byte)0);
			user.setLocked((byte)0);
			userService.add(user);
			return new ReturnValue<Long>(user.getId());
		}catch (Exception e) {
			log.error(e.getCause().getMessage());
			return new ReturnValue<Long>(ErrorCode.ERROR_SERVER_ERROR, "添加用户失败");
		}
	}

	/**
	 * 修改用户信息
	 * @Param: [user]
	 * @Author: chendingfengmail@163.com
	 * @Date: 2020/8/13
	 */
	@PutMapping(value = "/update")
	public ReturnValue<String> update(@RequestBody User user){
		try{
			// 加密password
			if (user.getPassword() != null && !user.getPassword().startsWith("$2a$10$")) {
				user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			}
			userService.update(user);
			return new ReturnValue<String>();
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValue<String>(ErrorCode.ERROR_SERVER_ERROR, "更新用户失败");
		}
	}

	/**
	 * 通过用户名删除用户
	 * @Param: [username]
	 * @Author: chendingfengmail@163.com
	 * @Date: 2020/8/13
	 */
	@DeleteMapping(value = "/delete/{username}")
	public ReturnValue<String> delete(@PathVariable(name="username", required=true) String username){
		try{
			userService.deleteByUserName(username);
			return new ReturnValue<String>();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getCause().getMessage());
			return new ReturnValue<String>(ErrorCode.ERROR_SERVER_ERROR, "删除用户失败");
		}
	}


	/**
	 * 分页查询满足指定条件的用户信息
	 * @Param: [pageIndex, pageSize, orderProp, order, username, mobile, ignore_self, admin]
	 * @Author: chendingfengmail@163.com
	 * @Date: 2020/8/13
	 */
	@GetMapping("/findByAttributes")
    public ReturnValue<List<User>> findByAttributes(@RequestParam(name="pageIndex") Long pageIndex, 
													@RequestParam(name="pageSize") Long pageSize,
													@RequestParam(name="orderProp", required=false) String orderProp,
													@RequestParam(name="order", required=false) String order,
													@RequestParam(name="userName",required=false) String userName,
													@RequestParam(name="userId", required=false) String userId,
													@RequestParam(name="enabled", required=false) Long enabled,
	    		                                    @RequestParam(name="mobile",required=false) String mobile){
    	try {
    		List<User> list = userService.findByAttributes(userName, userId , enabled, mobile, pageIndex, pageSize, orderProp, order);
    		if (CheckUtil.isNull(list)) {
				return new ReturnValue<List<User>>(ErrorCode.ERROR_NOT_FOUND, "未找到用户信息!");
			}
    		return new ReturnValue<List<User>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ReturnValue<List<User>>(ErrorCode.ERROR_SERVER_ERROR);
		}
    }
}
