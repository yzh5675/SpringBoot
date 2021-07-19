package com.cs.rbac.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@TableName("t_permission")
public class Permission implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String url;
	private Integer pid;
	private boolean open = true;
	private boolean checked = false;
	private String icon;
	
	@TableField(exist = false)
	private List<Permission> children = new ArrayList<Permission>();
	

}
