package com.cs.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.rbac.entity.Permission;
import com.cs.rbac.mapper.PermissionMapper;
import com.cs.rbac.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> queryPermissionsByUser(int id) {
		// TODO Auto-generated method stub
		return permissionMapper.queryPermissionsByUser(id);
	}

	@Override
	public List<Permission> queryAll() {
		// TODO Auto-generated method stub
		return permissionMapper.queryAll();
	}

	@Override
	public void insertPermission(Permission permission) {
		permissionMapper.insertPermission(permission.getName(), permission.getUrl(), permission.getPid());
	}

	@Override
	public Permission queryById(Integer id) {
		// TODO Auto-generated method stub
		return permissionMapper.selectById(id);
	}

	@Override
	public void updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionMapper.updateById(permission);
	}

	@Override
	public void deletePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionMapper.deleteById(permission.getId());
	}

	@Override
	public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
		// TODO Auto-generated method stub
		return permissionMapper.queryPermissionidsByRoleid(roleid);
	}
	
	
	
}
