package com.dingfeng.totoro.service.impl;

import com.dingfeng.totoro.mapper.UserMapper;
import com.dingfeng.totoro.model.User;
import com.dingfeng.totoro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @文件名称: UserServiceImpl
 * @功能描述:
 * @版权信息： www.dondown.com
 * @编写作者： chendingfengmail@163.com
 * @开发日期： 2020/9/24
 * @历史版本： V1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void add(User o) {
        userMapper.insertSelective(o);
    }

    @Override
    public void update(User o) {
        userMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public User findById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findByUserId(String userId){
        return userMapper.selectByUserId(userId);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public void deleteByUserName(String userName) {
        userMapper.deleteByUserName(userName);
    }

    @Override
    public Long findMaxByAttributes(String userName, String userId, Long enabled) {
        return null;
    }

    @Override
    public List<User> findByAttributes(String userName, String userId, Long enabled, String mobile, Long pageIndex, Long pageSize, String orderProp, String order) {
        return null;
    }


    @Override
    public void delete(User o) {
        userMapper.deleteByPrimaryKey(o.getId());
    }

}
