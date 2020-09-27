package com.dingfeng.totoro.oauth;

import com.dingfeng.totoro.model.Right;
import com.dingfeng.totoro.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户详情查询接口实现基类，提供基础的功能骨架，用户实现getUser接口提供用户查询实现即可
 */
public abstract class BaseUserDetailService implements UserDetailsService {
    @Autowired
    private RightService rightService;				// 权限服务

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	// 查找用户
    	com.dingfeng.totoro.model.User user = getUser(userName);
        if (null == user) {
            throw new UsernameNotFoundException("用户:" + userName + ",不存在!");
        }
        
        // 设置用户权限
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        List<Right> rights = rightService.findByUsername(user.getUsername());
        for(Right right : rights) {
    		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + right.getDescription());
            grantedAuthorities.add(authority);
    	}   	
        
        // 标识位设置
        boolean enabled = user.getEnabled() == 0 ? false : true; 			// 可用性 :true:可用 false:不可用
        boolean accountNonExpired = user.getExpired() == 0 ? true : false; 	// 过期性 :true:没过期 false:过期
        boolean credentialsNonExpired = true; 								// 有效性 :true:凭证有效 false:凭证无效
        boolean accountNonLocked = user.getLocked() == 0 ? true : false; 	// 锁定性 :true:未锁定 false:已锁定
  
        return new User(user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }

    /**
     * 用户信息查询抽象接口实现
     * @Param:
     * @Author: chendingfengmail@163.com
     * @Date: 2020/8/13
     */
    protected abstract com.dingfeng.totoro.model.User getUser(String var) ;
}
