package com.dingfeng.totoro.service;

/**
 * @文件名称: BaseService
 * @功能描述:
 * @版权信息： www.dondown.com
 * @编写作者： chendingfengmail@163.com
 * @开发日期： 2020/9/24
 * @历史版本： V1.0
 */
public interface BaseService<T> {
    /**
     * 往数据库插入一条记录，如果已存在则抛异常
     * @param o
     */
    public void add(T o);
    /**
     * 根据主键删除一条记录
     */
    public void delete(T o);
    /**
     * 根据对象id更新对象信息
     */
    public void update(T o);
    /**
     * 通过主键查询对象
     * @param id
     * @return
     */
    public T findById(Long id);
}
