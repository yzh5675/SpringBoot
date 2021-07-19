package com.cs.rbac.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cs.rbac.entity.Role;

public interface RoleService {

	public IPage<Role>  pageRoleData(int pageNum, int pageSize, String queryText);
	
	void insertRolePermission(Integer roleid, Integer[] permissionids);
	
	public List<Role> queryAll();
	
}
