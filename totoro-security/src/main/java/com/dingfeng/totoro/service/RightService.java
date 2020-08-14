package com.dingfeng.totoro.service;


import com.dingfeng.totoro.model.Right;

import java.util.List;

public interface RightService extends BaseService<Right>{
	/**
	 * 通过权限值名获取权限详情
	 * @param name
	 * @return
	 */
	public Right findByName(String name);
	
	/**
	 * 通过ID获取权限详情
	 * @return
	 */
	public Right findById(Long id);
	
	/**
	 * 通过角色ID获取权限详情
	 * @return
	 */
	public List<Right> findByRoleId(Long roleId);
	
	/**
	 * 通过用户名查询用户所有权限
	 * @param userName
	 * @return
	 */
    public List<Right> findByUsername(String userName);

	/**
	 * 通过用户名查询用户所有权限
	 * @return
	 */
	public Long findMaxByAttributes();
}
