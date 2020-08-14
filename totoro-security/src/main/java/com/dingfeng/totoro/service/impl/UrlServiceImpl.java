package com.dingfeng.totoro.service.impl;

import com.dingfeng.totoro.mapper.UrlMapper;
import com.dingfeng.totoro.model.SysUrl;
import com.dingfeng.totoro.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {
	@Autowired
	protected UrlMapper rightDetailMapper;

	@Override
	public void add(SysUrl o) {
		rightDetailMapper.insertSelective(o);
	}

	@Override
	public void delete(SysUrl o) {
		rightDetailMapper.deleteByPrimaryKey(o.getId());
	}

	@Override
	public void update(SysUrl o) {
		rightDetailMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public SysUrl findById(Object id) {
		return rightDetailMapper.selectByPrimaryKey((Long)id);
	}

	@Override
	public List<SysUrl> findByAttributes(Long pageIndex, Long pageSize) {
		return rightDetailMapper.selectByPage(pageIndex, pageSize);
	}

	@Override
	public List<SysUrl> findByUserId(Long userId) {
		return rightDetailMapper.selectByUserId(userId);
	}
}
