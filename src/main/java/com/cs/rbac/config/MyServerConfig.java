package com.cs.rbac.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServerConfig {

	@Bean
	public ServletListenerRegistrationBean<ServerStartupListener> myListener() {
		ServletListenerRegistrationBean<ServerStartupListener> rb = new ServletListenerRegistrationBean<ServerStartupListener>(new ServerStartupListener());
		return rb;
	}
	
}
