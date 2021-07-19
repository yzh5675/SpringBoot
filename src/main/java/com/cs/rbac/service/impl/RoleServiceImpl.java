package com.cs.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.rbac.entity.Role;
import com.cs.rbac.entity.RolePermission;
import com.cs.rbac.mapper.RoleMapper;
import com.cs.rbac.mapper.RolePermissionMapper;
import com.cs.rbac.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public IPage<Role> pageRoleData(int pageNum, int pageSize, String queryText) {
		// TODO Auto-generated method stub
		LambdaQueryWrapper<Role> roleLambdaQueryWrapper = Wrappers.lambdaQuery();
		roleLambdaQueryWrapper.like(Role::getName , queryText);
		Page<Role> rolePage = new Page<>(pageNum , pageSize);
        IPage<Role> roleIPage = roleMapper.selectPage(rolePage , roleLambdaQueryWrapper);
		return roleIPage;
	}

	@Override
	public void insertRolePermission(Integer roleid, Integer[] permissionids) {
		// TODO Auto-generated method stub
		QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("roleid", roleid);
		rolePermissionMapper.delete(queryWrapper);
		for (int i = 0; i < permissionids.length; i++) {
			RolePermission rp = new RolePermission();
			System.out.println("*****");
			System.out.println("roleid : " + roleid);
			System.out.println("permissionids : " + permissionids[i]);
			rp.setRoleid(roleid);
			rp.setPermissionid(permissionids[i]);
			rolePermissionMapper.insert(rp);
		}
		
	}

	@Override
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return roleMapper.selectList(null);
	}

	
}
