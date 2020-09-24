package com.dingfeng.totoro.mapper;

import com.dingfeng.totoro.model.Right;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Right record);

    int insertSelective(Right record);

    Right selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Right record);

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