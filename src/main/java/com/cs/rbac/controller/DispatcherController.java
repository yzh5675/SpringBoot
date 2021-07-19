package com.cs.rbac.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.rbac.entity.AJAXResult;
import com.cs.rbac.entity.Permission;
import com.cs.rbac.entity.User;
import com.cs.rbac.service.PermissionService;
import com.cs.rbac.service.UserService;

@Controller
public class DispatcherController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	
	@ResponseBody
	@RequestMapping("/doAJAXLogin")
	public Object doAJAXLogin(User user, HttpSession session) {
		
		AJAXResult result = new AJAXResult();
		
		User dbUser = userService.query4Login(user);
		if ( dbUser != null ) {
			System.out.println("===========" + dbUser);
			session.setAttribute("loginUser", dbUser);
			
			// 获取用户权限信息
			List<Permission> permissions = permissionService.queryPermissionsByUser(dbUser.getId());
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			Permission root = null;
			Set<String> uriSet = new HashSet<String>();
			for ( Permission permission : permissions ) {
				permissionMap.put(permission.getId(), permission);
				if ( permission.getUrl() != null && !"".equals(permission.getUrl()) ) {
					uriSet.add(session.getServletContext().getContextPath() + permission.getUrl());
				}
			}
			session.setAttribute("authUriSet", uriSet);
			for ( Permission permission : permissions ) {
				Permission child = permission;
				if ( child.getPid() == 0 ) {
					root = permission;
				} else {
					Permission parent = permissionMap.get(child.getPid());
					parent.getChildren().add(child);
				}
				
			}
			session.setAttribute("rootPermission", root);
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		
		return result;
	}
	
}
