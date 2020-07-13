package com.foxconn.iot.assets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UmsAdminCompanyDao {
	
	List<Long> queryCompanyRelation(@Param("userid") Long userid);
}
