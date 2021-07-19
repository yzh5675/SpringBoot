package com.cs.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@TableName("t_user_role")
public class UserRole {
	
	@TableId(value="urid", type=IdType.AUTO)
	private int urid;
	private int userid;
	private int roleid;

}
