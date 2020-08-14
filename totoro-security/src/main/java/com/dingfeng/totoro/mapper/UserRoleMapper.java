package com.dingfeng.totoro.mapper;


import com.dingfeng.totoro.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    List<UserRole> selectByUserId(Long userId);
    
    UserRole selectByUsername(String userName);
    
    List<UserRole> selectByRoleId(Long roleId);
    
    int deleteByRoleId(Long roleId);
    
    List<UserRole> selectByPage(@Param("pageIndex") Long pageIndex, @Param("pageSize") Long pageSize);

	void deleteByUserRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}