package com.cs.rbac.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cs.rbac.entity.User;

public interface UserService {

	List<User> queryAll();
	
	User query4Login(User user);
	
	//分页
	List<User> pageQueryData(String queryText, int start, int size);
	
	void insertUser(User user);
	
	User queryById(Integer id);
	
	void updateUser(User user);
	
	void deleteUserById(Integer id);
	
	void deleteUsers(List<Integer> ids);
	
	public IPage<User>  pageUserData(int pageNum, int pageSize, String queryText);
	
	public List<Integer> queryRoleidsByUserid(Integer id);
	
	//分配用户角色
	public void insertUserRoles(Integer userid, Integer[] unassignroleids);
	
	//取消分配用户角色
	public void deleteUserRoles(Integer userid, Integer[] assignroleids);
	
}
