package com.cs.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.rbac.entity.User;

@Repository
public interface UserMapper extends BaseMapper<User>{

	@Select("select roleid from t_user_role where userid = #{userid}")
	List<Integer> queryRoleidsByUserid(Integer userid);
	
}
