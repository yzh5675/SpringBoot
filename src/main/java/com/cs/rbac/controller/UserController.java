package com.cs.rbac.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cs.rbac.entity.AJAXResult;
import com.cs.rbac.entity.Page;
import com.cs.rbac.entity.Role;
import com.cs.rbac.entity.User;
import com.cs.rbac.service.RoleService;
import com.cs.rbac.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	//跳转页面
	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}
	
	//用户分页查询
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( String queryText, Integer pageno, Integer pagesize ) {
		AJAXResult result = new AJAXResult();
		try {
			
			// 分页查询
			IPage<User> puser = userService.pageUserData(pageno, pagesize, queryText);
			
			// 分页对象
			Page<User> userPage = new Page<User>();
			userPage.setDatas(puser.getRecords());
			userPage.setTotalno(puser.getPages());
			userPage.setTotalsize(puser.getSize());
			userPage.setPageno(puser.getCurrent());
			
			result.setData(userPage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/assign")
	public String assign( Integer id, Model model ) {
		
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		
		List<Role> roles = roleService.queryAll();
		
		List<Role> assingedRoles = new ArrayList<Role>();
		List<Role> unassignRoles = new ArrayList<Role>();
		
		// 获取关系表的数据
		List<Integer> roleids = userService.queryRoleidsByUserid(id);
		for ( Role role : roles ) {
			if ( roleids.contains(role.getId()) ) {
				assingedRoles.add(role);
			} else {
				unassignRoles.add(role);
			}
		}
		for (Role r : assingedRoles) {
			System.out.println("assingedRoles : " + r.getName());
		}
		
		for (Role r : unassignRoles) {
			System.out.println("unassignRoles : " + r.getName());
		}
		
		model.addAttribute("assingedRoles", assingedRoles);
		model.addAttribute("unassignRoles", unassignRoles);
		return "user/assign";
	}
	
	/**
	 * 分配的角色数据
	 */
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer userid, Integer[] unassignroleids ) {
		AJAXResult result = new AJAXResult();
		log.info("==userid=" + userid);
		log.info("==unassignroleids=" + Arrays.asList(unassignroleids));
		try {
			// 增加关系表数据
			userService.insertUserRoles(userid, unassignroleids);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	//取消分配的角色数据
	@ResponseBody
	@RequestMapping("/dounAssign")
	public Object dounAssign( Integer userid, Integer[] assignroleids ) {
		AJAXResult result = new AJAXResult();
		try {
			// 删除关系表数据
			userService.deleteUserRoles(userid, assignroleids);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	//去到修改页面
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	//修改用户
	@ResponseBody
	@RequestMapping("/update")
	public Object update( User user ) {
		AJAXResult result = new AJAXResult();
		
		try {
			
			userService.updateUser(user);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	//删除用户
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		AJAXResult result = new AJAXResult();
		try {
			userService.deleteUserById(id);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
}
