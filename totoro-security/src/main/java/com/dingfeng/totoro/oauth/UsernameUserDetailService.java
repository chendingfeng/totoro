package com.dingfeng.totoro.oauth;

import com.dingfeng.totoro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 通过用户名查询用户信息
 */
@Service
public class UsernameUserDetailService extends BaseUserDetailService {

    @Resource
    private UserService userService;

    @Override
    protected com.dingfeng.totoro.model.User getUser(String userName) {
        return userService.findByUserName(userName);
    }
}
