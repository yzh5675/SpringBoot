package com.cs.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.rbac.entity.Permission;

@Repository
public interface PermissionMapper extends BaseMapper<Permission>{

	@Select("select * from t_permission where id in (select permissionid "
			+ "from t_role_permission where roleid in (select roleid from t_user_role "
			+ "where userid = #{id}))")
	List<Permission> queryPermissionsByUser(int id);
	
	@Select("select * from t_permission")
	List<Permission> queryAll();
	
	@Insert("insert into t_permission (name, url, pid) values(#{name}, #{url}, #{pid})")
	void insertPermission(@Param("name") String name, @Param("url") String url, @Param("pid") int pid);
	
	@Select("select permissionid from t_role_permission where roleid = #{roleid}")
	List<Integer> queryPermissionidsByRoleid(Integer roleid);
}
