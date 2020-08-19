package com.foxconn.iot.assets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxconn.iot.assets.model.UmsAdminVo;
import com.foxconn.iot.assets.model.UmsMenu;

public interface UmsAdminDao {
	
	List<UmsMenu> queryByUsername(@Param("username") String username);
	
	List<Long> queryCompanyRelation(@Param("userid") Long userid);
	
	UmsAdminVo queryInfo(@Param("username") String username);
	
	List<String> matchUsernames(@Param("companyId") Long companyId, @Param("usernames") List<String> usernames);
}
