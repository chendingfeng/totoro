package com.dingfeng.totoro.mapper;


import com.dingfeng.totoro.model.WechatApp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WechatAppMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatApp record);

    int insertSelective(WechatApp record);

    WechatApp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatApp record);

    int updateByPrimaryKey(WechatApp record);
    
    List<WechatApp> selectByUsernameOrOpenId(@Param("username") String username, @Param("openId") String openId, @Param("type") Long type);

	List<WechatApp> selectByAttributes(@Param("pageIndex") Long pageIndex, @Param("pageSize") Long pageSize,
                                       @Param("username") String username, @Param("openId") String openId, @Param("type") Long type);
}