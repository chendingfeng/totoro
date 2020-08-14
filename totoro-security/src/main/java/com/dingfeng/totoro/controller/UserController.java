package com.dingfeng.totoro.controller;

import com.dingfeng.totoro.error.ErrorCode;
import com.dingfeng.totoro.error.ReturnValue;
import com.dingfeng.totoro.model.User;
import com.dingfeng.totoro.service.UserService;
import com.dingfeng.totoro.util.CheckUtil;
import io.swagger.annotations.Api;
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
	@PostMapping(value = "/add")
	public ReturnValue<Long> add(@RequestHeader(name="current_user",required=false) String current_user,
								 @RequestHeader(name="current_user_type",required=false) String current_user_type,
								 @RequestBody User user){
		try {
			// 同名检测
			User temp = userService.findByUsername(user.getUsername());
			if (!CheckUtil.isNull(temp)) {
				return new ReturnValue<Long>(ErrorCode.ERROR_OBJECT_EXIST, "该用户名已存在!");
			}
			// 手机号检测
			temp = userService.findByCellphone(user.getMobile(), (long)user.getUserType());
			if (!CheckUtil.isNull(temp)) {
				return new ReturnValue<Long>(ErrorCode.ERROR_OBJECT_EXIST, "该类型用户手机号已存在!");
			}
			// 加密password
			user.setCreateUser(current_user);
			user.setPassword("888888");
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			// 默认值字段赋默认值
			user.setAdmin((byte)0);
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
	
	@PutMapping(value = "/updateUserCellphone")
	public ReturnValue<String> updateUserCellphone( @RequestParam(name="userType",required=true) Long userType, 
													@RequestParam(name="oldCellphone",required=true) String oldCellphone,  
													@RequestParam(name="cellphone",required=true) String cellphone){
		try{
			// 手机号相同
			if (cellphone.equals(oldCellphone)) {
				return new ReturnValue<String>(ErrorCode.ERROR_OBJECT_EXIST, "此手机号码不能与原手机号相同!");
			}
			// 手机号检测
			User u = userService.findByCellphone(cellphone, userType);
			if (!CheckUtil.isNull(u)) {
				return new ReturnValue<String>(ErrorCode.ERROR_OBJECT_EXIST, "此手机号已被占用,请更换其他手机号!");
			}
						
			List<User> users = userService.findByAttributes(null, oldCellphone, userType, null, null, null, null, null, null, 0L, 1L);
			if (CheckUtil.isNull(users)) {
				return new ReturnValue<String>(ErrorCode.ERROR_NOT_FOUND, "指定类型的用户原手机号不存在!");
			}
			
			User newUser = new User();
			newUser.setId(users.get(0).getId());
			newUser.setMobile(cellphone);			
			
			userService.update(newUser);
			return new ReturnValue<String>();
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValue<String>(ErrorCode.ERROR_SERVER_ERROR, "更新用户手机号失败");
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
			userService.deleteByUsername(username);
			return new ReturnValue<String>();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getCause().getMessage());
			return new ReturnValue<String>(ErrorCode.ERROR_SERVER_ERROR, "删除用户失败");
		}
	}


	/**
	 * 查询满足指定条件的用户数量
	 * @Param: [username, phone, userType, relativeId, current_user, ignore_self, admin]
	 * @Author: chendingfengmail@163.com
	 * @Date: 2020/8/13
	 */
	@GetMapping("/findMaxByAttributes")
    public ReturnValue<Long> findMaxByAttributes(@RequestParam(name="username",required=false) String username,
    		                                     @RequestParam(name="phone",required=false) String phone,
												 @RequestParam(name="userType", required=false) Long userType,
												 @RequestParam(name="relativeId",required=false) String relativeId,
												 @RequestHeader(name="current_user",required=false) String current_user,
												 @RequestParam(name="ignore_self",required=false) Long ignore_self,
												 @RequestParam(name="admin",required=false) Long admin){
    	try {
    		String ignoreUser = null;
    		if(ignore_self != null && ignore_self != 0){
    			ignoreUser = current_user;
    		}
    		Long count = userService.findMaxByAttributes(username, phone, userType, relativeId, ignoreUser, admin, current_user);
    		return new ReturnValue<Long>(count);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ReturnValue<Long>(ErrorCode.ERROR_SERVER_ERROR);
		}
    }

	/**
	 * 分页查询满足指定条件的用户信息
	 * @Param: [pageIndex, pageSize, orderProp, order, username, phone, userType, relativeId, current_user, ignore_self, admin]
	 * @Author: chendingfengmail@163.com
	 * @Date: 2020/8/13
	 */
	@GetMapping("/findByAttributes")
    public ReturnValue<List<User>> findByAttributes(@RequestParam(name="pageIndex") Long pageIndex, 
													@RequestParam(name="pageSize") Long pageSize,
													@RequestParam(name="orderProp", required=false) String orderProp,
													@RequestParam(name="order", required=false) String order,
													@RequestParam(name="username",required=false) String username,
	    		                                    @RequestParam(name="phone",required=false) String phone,
													@RequestParam(name="userType", required=false) Long userType,
													@RequestParam(name="relativeId",required=false) String relativeId,
													@RequestHeader(name="current_user",required=false) String current_user,
													@RequestParam(name="ignore_self",required=false) Long ignore_self,
													@RequestParam(name="admin",required=false) Long admin){
    	try {
    		String ignoreUser = null;
    		if(ignore_self != null && ignore_self != 0){
    			ignoreUser = current_user;
    		}
    		List<User> list = userService.findByAttributes(username, phone, userType, relativeId, ignoreUser, admin, current_user, orderProp, order, pageIndex, pageSize);
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
