package com.dingfeng.totoro.service.impl;

import com.dingfeng.totoro.error.ErrorCode;
import com.dingfeng.totoro.exception.TotoroException;
import com.dingfeng.totoro.mapper.RoleMapper;
import com.dingfeng.totoro.mapper.UserMapper;
import com.dingfeng.totoro.mapper.UserRoleMapper;
import com.dingfeng.totoro.mapper.WechatAppMapper;
import com.dingfeng.totoro.model.Role;
import com.dingfeng.totoro.model.User;
import com.dingfeng.totoro.model.UserRole;
import com.dingfeng.totoro.model.WechatApp;
import com.dingfeng.totoro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private WechatAppMapper wechatAppMapper;

	@Override
	public void add(User o) { 
		userMapper.insertSelective(o);
	}

	@Override
	public User findByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	@Override
	public void update(User o) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(o);
	}
	
	@Override
	public User findByUserId(long userId){
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public User findByCellphone(String cellPhone, Long userType) {
		return userMapper.selectCellphone(cellPhone, userType);
	}

	@Override
	public void delete(User o) {
		userMapper.deleteByPrimaryKey(o.getId());
	}

	@Override
	public User findById(Object id) {
		return userMapper.selectByPrimaryKey((Long)id);
	}

	@Override
	public List<User> findByAttributes(Long pageIndex, Long pageSize) {
		return userMapper.selectByPage(pageIndex,pageSize);
	}

	@Override
	public void deleteByUserName(String username) {
		userMapper.deleteByUserName(username);
	}

	@Override
	public void deleteByUsername(String username) {
		userMapper.deleteByUserName(username);
	}

	@Override
	public List<User> findByRoleId(Long roleId) {
		return userMapper.selectByRoleId(roleId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createUserAndAssignRole(User user, String roleName) throws TotoroException {
		// 增加用户
		userMapper.insertSelective(user);
		// 查询对应角色
		Role role = roleMapper.selectByRoleName(roleName);
		if (null == role) {
			throw new TotoroException(ErrorCode.ERROR_NOT_FOUND.getError(), "角色不存在：" + roleName);
		}
		// 用户角色信息
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(role.getId());
		userRoleMapper.insertSelective(userRole);
	}

	@Override
	public Long findMaxByAttributes(String username, String phone, Long userType, String relativeId, String ignoreUser, Long admin, String createUser) {
		return userMapper.selectMaxByAttributes(username, phone, userType, relativeId, ignoreUser, admin, createUser);
	}

	@Override
	public List<User> findByAttributes(String username, String phone, Long userType, String relativeId, String ignoreUser, 
			Long admin, String createUser, String orderProp, String order, Long pageIndex, Long pageSize) {
		return userMapper.selectByAttributes(username, phone, userType, relativeId, ignoreUser, admin, createUser, orderProp, order, pageIndex, pageSize);
	}

	@Override
	public void deleteByRelativeId(String relativeId) {
		userMapper.deleteByRelativeId(relativeId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createUserRoleAndAddWechatApp(User user, String roleName, WechatApp app) throws TotoroException {
		// 增加用户
		userMapper.insertSelective(user);
		// 查询对应角色
		Role role = roleMapper.selectByRoleName(roleName);
		if (null == role) {
			throw new TotoroException(ErrorCode.ERROR_NOT_FOUND.getError(), "角色不存在：" + roleName);
		}
		// 用户角色信息
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(role.getId());
		userRoleMapper.insertSelective(userRole);
		// 添加用户app信息
		if(app != null) {
			wechatAppMapper.insertSelective(app);
		}
	}

	@Override
	public User findByOpenId(Long userType, String openId, Long openIdType) {
		return userMapper.selectByOpenId(userType, openId, openIdType);
	}

}
