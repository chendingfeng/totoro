package com.dingfeng.totoro.service.impl;

import com.dingfeng.totoro.model.RightItem;
import com.dingfeng.totoro.service.RightItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightItemServiceImpl implements RightItemService {
	@Autowired
	protected com.dingfeng.totoro.mapper.RightItemMapper RightItemMapper;

	@Override
	public void add(RightItem o) {
		RightItemMapper.insertSelective(o);
	}

	@Override
	public void delete(RightItem o) {
		RightItemMapper.deleteByPrimaryKey(o.getId());
	}
	
	@Override
	public void update(RightItem o) {
		RightItemMapper.updateByPrimaryKeySelective(o);
	}


	@Override
	public List<RightItem> findByAttributes(Long pageIndex, Long pageSize) {
		return RightItemMapper.selectByPage(pageIndex, pageSize);
	}

	@Override
	public RightItem findById(Object id) {
		return RightItemMapper.selectByPrimaryKey((Long)id);
	}
}
