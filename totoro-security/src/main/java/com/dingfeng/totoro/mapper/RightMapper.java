package com.dingfeng.totoro.mapper;

import com.dingfeng.totoro.model.Right;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightMapper {
	/**
	 * 通过主键ID删除权限
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
	 * 插入一条权限记录
	 * @param record
	 * @return
	 */
    int insert(Right record);

    /**
	 * 插入一条权限记录[不包含空字段]
	 * @param record
	 * @return
	 */
    int insertSelective(Right record);

    /**
	 * 通过权限ID检索权限
	 * @param id
	 * @return
	 */
    Right selectByPrimaryKey(Long id);

    /**
	 * 通过ID更新权限非空字段
	 * @param record
	 * @return
	 */
    int updateByPrimaryKeySelective(Right record);

    /**
	 * 通过ID更新权限所有字段
	 * @param record
	 * @return
	 */
    int updateByPrimaryKey(Right record);
    
    /**
   	 * 获取所有权限列表
   	 * @return
   	 */
    List<Right> selectAll();
    
    /**
   	 * 获通过权限名获取对应权限
   	 * @param name
   	 * @return
   	 */
    Right selectByName(String name);
    
    /**
   	 * 获通过用户名获取用户权限列表
   	 * @param userName
   	 * @return
   	 */
    List<Right> selectByUsername(String userName);
    
    /**
   	 * 获通角色获取用户权限列表
   	 * @param roleId
   	 * @return
   	 */
    List<Right> selectByRoleId(Long roleId);

    /**
     * 分页获取用户信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
	List<Right> selectByPage(@Param("pageIndex") Long pageIndex, @Param("pageSize") Long pageSize);

	/**
     * 根据用户名删除用户信息
     * @param username：用户名
     * @return
     */
	int deleteByUserName(@Param("username") String username);

	/**
	 * 根据权限名删除权限信息
	 * @param name ： 权限名称
	 * @return
	 */
	int deleteByRightName(@Param("name") String name);

	/**
	 * 获取权限总数
	 * @return
	 */
	Long selectMaxByAttributes();
}