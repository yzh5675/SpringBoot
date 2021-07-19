package com.cs.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@TableName("t_role_permission")
public class RolePermission {

	@TableId(value="rpid", type=IdType.AUTO)
	private Integer rpid;
	private Integer roleid;
	private Integer permissionid;
	
}
