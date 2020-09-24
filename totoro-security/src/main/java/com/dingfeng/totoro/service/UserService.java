package com.dingfeng.totoro.service;

import com.dingfeng.totoro.model.User;

import java.util.List;

/**
 * @文件名称: UserService
 * @功能描述:
 * @版权信息： www.dondown.com
 * @编写作者： chendingfengmail@163.com
 * @开发日期： 2020/9/24
 * @历史版本： V1.0
 */
public interface UserService extends BaseService<User> {
    /**
     *
     * @param  id
     * @return
     */
    public User findById(Long id);

    /**
     *
     * @param userName
     * @return
     */
    public User findByUserName(String userName);


    /**
     *
     * @param userId
     * @return
     */
    public User findByUserId(String userId);

    /**
     *
     * @param userName
     * @return
     */
    public void deleteByUserName(String userName);

    /**
     *
     * @param userName
     * @param userId
     * @param enabled
     * @return
     */
    public Long findMaxByAttributes(String userName, String userId, Long enabled);

    /**
     *
     * @param userName
     * @param userId
     * @param enabled
     * @param pageSize
     * @param orderProp
     * @param order
     * @return
     */
    public List<User> findByAttributes(String userName, String userId , Long enabled, String mobile, Long pageIndex, Long pageSize, String orderProp, String order);

}
