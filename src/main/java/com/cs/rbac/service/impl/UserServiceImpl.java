package com.cs.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.rbac.entity.User;
import com.cs.rbac.entity.UserRole;
import com.cs.rbac.mapper.UserMapper;
import com.cs.rbac.mapper.UserRoleMapper;
import com.cs.rbac.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return userMapper.selectList(null);
	}

	@Override
	public User query4Login(User user) {
		// TODO Auto-generated method stub
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("loginacct", user.getLoginacct());
		queryWrapper.eq("userpswd", user.getUserpswd());
		return userMapper.selectOne(queryWrapper);
	}

	@Override
	public List<User> pageQueryData(String queryText, int start, int size) {
		// TODO Auto-generated method stub
		LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(User::getLoginacct , queryText);
        Page<User> userPage = new Page<>(start , size);
        IPage<User> userIPage = userMapper.selectPage(userPage , userLambdaQueryWrapper);
		return userIPage.getRecords();
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

	@Override
	public User queryById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectById(id);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateById(user);
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		userMapper.deleteById(id);
	}

	@Override
	public void deleteUsers(List<Integer> ids) {
		// TODO Auto-generated method stub
		userMapper.deleteBatchIds(ids);
	}

	@Override
	public IPage<User> pageUserData(int pageNum, int pageSize, String queryText) {
		// TODO Auto-generated method stub
		LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
		userLambdaQueryWrapper.like(User:: getUsername , queryText);
		Page<User> userPage = new Page<>(pageNum , pageSize);
        IPage<User> userIPage = userMapper.selectPage(userPage , userLambdaQueryWrapper);
		return userIPage;
	}

	//根据用户ID获取他的角色ID
	@Override
	public List<Integer> queryRoleidsByUserid(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.queryRoleidsByUserid(id);
	}

	//分配用户角色
	@Override
	public void insertUserRoles(Integer userid, Integer[] unassignroleids) {
		// TODO Auto-generated method stub
		for (Integer integer : unassignroleids) {
			UserRole ur = new UserRole();
			ur.setUserid(userid);
			ur.setRoleid(integer);
			userRoleMapper.insert(ur);
		}
		
	}

	//取消分配用户角色
	@Override
	public void deleteUserRoles(Integer userid, Integer[] assignroleids) {
		// TODO Auto-generated method stub
		for (Integer integer : assignroleids) {
			QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("userid", userid);
			queryWrapper.eq("roleid", integer);
			userRoleMapper.delete(queryWrapper);
		}
	}

}
