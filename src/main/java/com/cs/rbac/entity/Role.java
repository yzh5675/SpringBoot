package com.cs.rbac.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@TableName("t_role")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;
	
	private String name;
	
}
