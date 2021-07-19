package com.cs.rbac.service;

import java.util.List;

import com.cs.rbac.entity.Permission;

public interface PermissionService {

	List<Permission> queryPermissionsByUser(int id);
	
	public List<Permission> queryAll();
	
	public void insertPermission(Permission permission);
	
	Permission queryById(Integer id);
	
	void updatePermission(Permission permission);
	
	void deletePermission(Permission permission);
	
	List<Integer> queryPermissionidsByRoleid(Integer roleid);
}
