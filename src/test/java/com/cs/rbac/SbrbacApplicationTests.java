package com.cs.rbac;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cs.rbac.entity.Role;
import com.cs.rbac.entity.User;
import com.cs.rbac.service.RoleService;
import com.cs.rbac.service.UserService;

@SpringBootTest
class SbrbacApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@Test
	public void test01() {
		List<User> users = userService.queryAll();
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void test02() {
		User user = new User();
		user.setLoginacct("admin");
		user.setUserpswd("admin");
		System.out.println(userService.query4Login(user));
	}
	
	@Test
	public void test03() {
		List<Role> rs = roleService.queryAll();
		for (Role role : rs) {
			System.out.println(role);
		}
	}

}
