package com.dingfeng.totoro.service;


import com.dingfeng.totoro.exception.TotoroException;
import com.dingfeng.totoro.model.User;
import com.dingfeng.totoro.model.WechatApp;

import java.util.List;

/**
 * 提供用户服务接口
 * @author Administrator
 */
public interface UserService extends BaseService<User>{
	/**
	 * 用户用户名获取用户信息
	 * @param userName
	 * @return
	 */
	public User findByUsername(String userName);
	
	/**
	 * 通过手机号获取用户信息
	 * @param cellPhone
	 * @param userType:见枚举UserType
	 * @return
	 */
	public User findByCellphone(String cellPhone, Long userType);
	
	/**
	 * 通过用户id获取用户信息
	 * @param userId
	 * @return
	 */
	public User findByUserId(long userId);

	/**
	 * 通过用户openId获取用户信息
	 * @param userType
	 * @param openId
	 * @param openIdType
	 * @return
	 */
	public User findByOpenId(Long userType, String openId, Long openIdType);

	/**
	 * 查询用户最大数
	 * @param username
	 * @param phone
	 * @param userType
	 * @param relativeId
	 * @param ignoreUser
	 * @param admin
	 * @param createUser
	 * @return
	 */
	public Long findMaxByAttributes(String username, String phone, Long userType, String relativeId, String ignoreUser, Long admin, String createUser);

	/**
	 * 通过属性查找所有用户信息
	 * @param username
	 * @param phone
	 * @param userType
	 * @param relativeId
	 * @param ignoreUser
	 * @param admin
	 * @param createUser
	 * @param orderProp
	 * @param order
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<User> findByAttributes(String username, String phone, Long userType, String relativeId,
                                       String ignoreUser, Long admin, String createUser, String orderProp, String order, Long pageIndex, Long pageSize);
	
	/**
	 * 通过用户名删除用户
	 * @param userName
	 * @return
	 */
	public void deleteByUserName(String userName);

	/**
	 * 通过角色id查找拥有某角色的用户
	 * @param roleId
	 * @return
	 */
	public List<User> findByRoleId(Long roleId);

	/**
	 * 通过用户名删除用户
	 * @param username
	 */
	public void deleteByUsername(String username);

	/**
	 * 通过用户名删除用户
	 * @param user
	 * @param roleName
	 * @throws TotoroException
	 */
	public void createUserAndAssignRole(User user, String roleName) throws TotoroException;

	/**
	 * 创建用户并分配角色及添加用户app信息
	 * @param user
	 * @param roleName
	 * @param app
	 * @throws TotoroException
	 */
	public void createUserRoleAndAddWechatApp(User user, String roleName, WechatApp app) throws TotoroException;


	/**
	 * 根据用户关联id删除所有用户
	 * @param relativeId
	 */
	public void deleteByRelativeId(String relativeId);


}