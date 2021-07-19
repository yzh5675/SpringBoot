package com.cs.rbac.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.rbac.entity.AJAXResult;
import com.cs.rbac.entity.Permission;
import com.cs.rbac.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;

	/**
	 * 权限管理首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "permission/index";
	}
	
	//建立权限树
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		List<Permission> permissions = new ArrayList<Permission>();

		// 查询所有的许可数据
		List<Permission> ps = permissionService.queryAll();
		
		
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for (Permission p : ps) {
			permissionMap.put(p.getId(), p);
		}
		for ( Permission p : ps ) {
			Permission child = p;
			if ( child.getPid() == 0 ) {
				permissions.add(p);
			} else {
				Permission parent = permissionMap.get(child.getPid());
				parent.getChildren().add(child);
			}
		}
		
		return permissions;
	}
	
	//跳转至添加权限页面
	@RequestMapping("/add")
	public String add() {
		return "permission/add";
	}
	
	//添加权限
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Permission permission ) {
		AJAXResult result = new AJAXResult();
		System.out.println(permission);
		try {
			permissionService.insertPermission(permission);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	//跳转到 修改页面
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		Permission permission = permissionService.queryById(id);
		model.addAttribute("permission", permission);
		
		return "permission/edit";
	}
	
	//修改权限表
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Permission permission ) {
		AJAXResult result = new AJAXResult();
		
		try {
			permissionService.updatePermission(permission);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	//删除权限节点
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Permission permission ) {
		AJAXResult result = new AJAXResult();
		
		try {
			permissionService.deletePermission(permission);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	// 获取当前角色已经分配的许可信息
	@ResponseBody
	@RequestMapping("/loadAssignData")
	public Object loadAssignData( Integer roleid ) {
		List<Permission> permissions = new ArrayList<Permission>();
		List<Permission> ps = permissionService.queryAll();
		
		// 获取当前角色已经分配的许可信息
		List<Integer> permissionids = permissionService.queryPermissionidsByRoleid(roleid);
		
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for (Permission p : ps) {
			if ( permissionids.contains(p.getId()) ) {
				p.setChecked(true);
			} else {
				p.setChecked(false);
			}
			permissionMap.put(p.getId(), p);
		}
		for ( Permission p : ps ) {
			Permission child = p;
			if ( child.getPid() == 0 ) {
				permissions.add(p);
			} else {
				Permission parent = permissionMap.get(child.getPid());
				parent.getChildren().add(child);
			}
		}
		return permissions;
	}
	
}
