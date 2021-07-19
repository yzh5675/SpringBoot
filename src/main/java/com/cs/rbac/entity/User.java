package com.cs.rbac.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;


@ToString
@Data
@TableName("t_user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@TableId
	private Integer id;
	private String username;
	private String loginacct;
	private String userpswd;
	private String email;
	private String createtime;
	

}
