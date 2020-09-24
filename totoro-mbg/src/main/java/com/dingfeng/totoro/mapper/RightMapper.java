package com.dingfeng.totoro.mapper;

import com.dingfeng.totoro.model.Right;

public interface RightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Right record);

    int insertSelective(Right record);

    Right selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Right record);

    int updateByPrimaryKey(Right record);
}