package com.cs.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cs.rbac.entity.AJAXResult;
import com.cs.rbac.entity.Page;
import com.cs.rbac.entity.Role;
import com.cs.rbac.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	public String index() {
		return "role/index";
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( String queryText, Integer pageno, Integer pagesize ) {
		
		AJAXResult result = new AJAXResult();
		
		try {
			IPage<Role> prole = roleService.pageRoleData(pageno, pagesize, queryText);
			
			// 分页对象
			Page<Role> rolePage = new Page<Role>();
			rolePage.setDatas(prole.getRecords());
			rolePage.setTotalno(prole.getPages());
			rolePage.setTotalsize(prole.getTotal());
			rolePage.setPageno(prole.getCurrent());
			
			result.setData(rolePage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/assign")
	public String assign() {
		return "role/assign";
	}
	
	//给角色分配权限
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer roleid, Integer[] permissionids ) {
		AJAXResult result = new AJAXResult();
		
		System.out.println("=========");
		System.out.println(roleid);
		System.out.println(permissionids);
		
		try {
			roleService.insertRolePermission(roleid, permissionids);
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
