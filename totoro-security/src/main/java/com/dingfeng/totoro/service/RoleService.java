package com.dingfeng.totoro.service;

import com.dingfeng.totoro.model.Role;

import java.util.List;

/**
 * @Description: 角色服务接口
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
public interface RoleService  extends BaseService<Role>{
	/**
	 * 通过id获取角色信息
	 * @param id
	 * @return
	 */
	public Role findById(Long id);
	
	/**
	 * 通过用户id获取用户角色列表
	 * @param userId
	 * @return
	 */
	public List<Role> findByUserId(Long userId);
	
	/**
	 * 通过用户名获取用户角色列表
	 * @param userName
	 * @return
	 */
	public List<Role> findByUserName(String userName);
	
	/**
	 * 查找所有角色
	 * @return
	 */
	public List<Role> findAll();


	/**
	 * 通过角色名获取角色信息
	 * @param roleName
	 * @param relativeId
	 * @param userType
	 * @param createUser
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Role> findByAttributes(String roleName, String relativeId, Long userType, String createUser, Long pageIndex, Long pageSize);

	/**
	 * 查询数量
	 * @param roleName
	 * @param relativeId
	 * @param userType
	 * @param createUser
	 * @return
	 */
	public Long findMaxByAttributes(String roleName, String relativeId, Long userType, String createUser);
}
