package com.dingfeng.totoro.mapper;


import com.dingfeng.totoro.model.RightItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightItemMapper {

    /**
     * 通过权限项目明细id删除权限项目
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加权限项目明细信息-全部列
     * @param record
     * @return
     */
    int insert(RightItem record);

    /**
     * 添加权限项目明细信息-非空列
     * @param record
     * @return
     */
    int insertSelective(RightItem record);

    /**
     * 通过主键搜索
     * @param id
     * @return
     */
    RightItem selectByPrimaryKey(Long id);

    /**
     * 通过角色id搜索角色权限项目
     * @param roleId
     * @return
     */
    List<RightItem> selectByRoleId(@Param("roleId") Long roleId);

    /**
     * 通过用户id或用户名获取用户权限项目
     * @param userId
     * @param username
     * @return
     */
    List<RightItem> selectByUserIdOrName(@Param("userId") Long userId, @Param("username") String username);

    /**
     * 通过主键更新非空列
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RightItem record);

    /**
     * 通过主键更新所有列
     * @param record
     * @return
     */
    int updateByPrimaryKey(RightItem record);

    /**
     * 分页搜索所有权限项目信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<RightItem> selectByPage(@Param("pageIndex") Long pageIndex, @Param("pageSize") Long pageSize);

    /**
     * 通过权限项目URL搜索
     * @param url
     * @return
     */
    RightItem selectByUrl(@Param("url") String url);

    /**
     * 搜索权限项目的所权限项目明细
     * @param rightId
     * @return
     */
    List<RightItem> selectByRightId(Long rightId);
}