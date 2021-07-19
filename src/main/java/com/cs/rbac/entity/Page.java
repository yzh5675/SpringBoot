package com.cs.rbac.entity;

import java.util.List;
import lombok.Data;

@Data
public class Page<T> {

	private List<T> datas;
	private long pageno;
	private long totalno;
	private long totalsize;
	
}
