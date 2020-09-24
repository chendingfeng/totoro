package com.dingfeng.totoro.service;

import com.dingfeng.totoro.model.Right;

import java.util.List;

/**
 * @文件名称: RightService
 * @功能描述:
 * @版权信息： www.dondown.com
 * @编写作者： chendingfengmail@163.com
 * @开发日期： 2020/9/24
 * @历史版本： V1.0
 */
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
