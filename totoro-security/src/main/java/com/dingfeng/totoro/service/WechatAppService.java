package com.dingfeng.totoro.service;

import com.dingfeng.totoro.model.WechatApp;

import java.util.List;

/**
 * @Description: 提供微信账号登录验证服务
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
public interface WechatAppService extends BaseService<WechatApp>{

	/**
	 * 通过手机号和openid及类型验证用户
	 * @param username
	 * @param openId
	 * @param type
	 * @return
	 */
	public List<WechatApp> findByUsernameOrOpenId(String username, String openId, Long type);

	/**
	 * 通过属性分页获取
	 * @param pageIndex
	 * @param pageSize
	 * @param username
	 * @param openId
	 * @param type
	 * @return
	 */
	public List<WechatApp> findByAttributes(Long pageIndex, Long pageSize, String username, String openId, Long type);
}