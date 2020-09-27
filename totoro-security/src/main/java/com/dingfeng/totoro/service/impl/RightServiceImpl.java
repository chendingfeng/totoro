package com.dingfeng.totoro.service.impl;

import com.dingfeng.totoro.mapper.RightMapper;
import com.dingfeng.totoro.model.Right;
import com.dingfeng.totoro.service.RightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @文件名称: RightServiceImpl
 * @功能描述:
 * @版权信息： www.dondown.com
 * @编写作者： chendingfengmail@163.com
 * @开发日期： 2020/9/24
 * @历史版本： V1.0
 */
@Service
public class RightServiceImpl implements RightService {

    @Resource
    private RightMapper rightMapper;

    @Override
    public Right findByName(String name) {
        return null;
    }

    @Override
    public void add(Right o) {

    }

    @Override
    public void delete(Right o) {

    }

    @Override
    public void update(Right o) {

    }

    @Override
    public Right findById(Long id) {
        return null;
    }

    @Override
    public List<Right> findByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<Right> findByUsername(String userName) {
        return rightMapper.selectByUsername(userName);
    }

    @Override
    public Long findMaxByAttributes() {
        return null;
    }
}
