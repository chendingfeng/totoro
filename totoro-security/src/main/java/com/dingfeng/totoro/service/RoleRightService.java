package com.dingfeng.totoro.service;


import com.dingfeng.totoro.model.RoleRight;

import java.util.List;

public interface RoleRightService extends BaseService<RoleRight>{
	/**
	 * 重置用户权限
	 * @param records
	 * @return
	 */
	public int resetRoleRights(Long roleId, List<RoleRight> records);
	
	/**
	 * 查询角色对应权限
	 * @param roleId
	 * @return
	 */
	public List<RoleRight> findByRoleId(Long roleId);
	
	/**
	 * 通过权限ID查询有改权限的角色ID
	 * @param rightId
	 * @return
	 */
    public List<RoleRight> findByRightId(Long rightId);
    
    /**
     * 通过角色名称查找角色权限信息
     * @param roleName
     * @return
     */
    public List<RoleRight> findByRoleName(String roleName);
    
	/**
	 * 通过权限ID查询有改权限的角色ID
	 * @param roleId
	 * @param rightId
	 * @return
	 */
    public RoleRight findByRoleRightId(Long roleId, Long rightId);

    /**
     * 通过属性查找角色权限信息
     * @param
     * @return
     */
	public List<RoleRight> findByAttributes(Long roleId, Long rightId, Long pageIndex, Long pageSize);

	/**
	 * 获取角色权限数量
	 * @param
	 * @return
	 */
	public List<RoleRight> findMaxByAttributes(Long roleId, Long rightId);
}
