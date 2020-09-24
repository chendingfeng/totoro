package com.dingfeng.totoro.mapper;

import com.dingfeng.totoro.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void deleteByUserName(@Param("userName")String userName);

    User selectByUserName(@Param("userName")String userName);

    User selectByUserId(@Param("userId")String userId);

    Long selectMaxByAttributes(@Param("userName")String userName, @Param("type")Long type, @Param("userId")String userId, @Param("enabled")Long enabled);

    List<User> selectByAttributes(@Param("userName")String userName,  @Param("userId")String userId,
                                  @Param("enabled")Long enabled, @Param("pageIndex")Long pageIndex, @Param("pageSize")Long pageSize,
                                  @Param("orderProp")String orderProp, @Param("order")String order);
}