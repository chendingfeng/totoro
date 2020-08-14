package com.dingfeng.totoro.service;

import com.dingfeng.totoro.model.SysUrl;

import java.util.List;

public interface UrlService extends BaseService<SysUrl>{

	/**
	 * 通过用户id 查询接口列表
	 * @param userId
	 * @return
	 */
	public List<SysUrl> findByUserId(Long userId);
}
