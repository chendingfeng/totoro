package com.dingfeng.totoro.service;


import com.dingfeng.totoro.model.UserRole;

import java.util.List;

/**
 * 提供用户角色服务接口
 * @author Administrator
 */
public interface UserRoleService extends BaseService<UserRole>{
	/**
	 * 通过用户ID查找用户角色信息
	 * @param id
	 * @return
	 */
    List<UserRole> findByUserId(Long id);
    
    /**
     * 通过用户名查找用户角色信息
     * @param userName
     * @return
     */
    UserRole findByUsername(String userName);
    
    /**
     * 通过角色ID获取用户角色信息
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(Long roleId);
    
    /**
     * 通过角色Id删除用户信息
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);

	/**
	 * 分页获取用户角色信息
	 * @param pageIndex 初始页-从1开始
	 * @param pageSize 每页数量
	 * @return
	 */
    List<UserRole> findByAttributes(Long pageIndex, Long pageSize);

	/**
	 * 通过用户id和角色id删除用户角色
	 * @param userId
	 * @param roleId
	 */
	void deleteByUserRoleId(Long userId, Long roleId);
}