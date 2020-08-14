package com.dingfeng.totoro.oauth;

import com.dingfeng.totoro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通过用户名查询用户信息
 */
@Service
public class UsernameUserDetailService extends BaseUserDetailService {

    @Autowired
    private UserService userService;

    @Override
    protected com.dingfeng.totoro.model.User getUser(String userName) {
        return userService.findByUsername(userName);
    }
}
