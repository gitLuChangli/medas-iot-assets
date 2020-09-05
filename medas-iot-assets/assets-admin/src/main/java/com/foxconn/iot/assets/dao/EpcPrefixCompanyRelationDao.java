package com.foxconn.iot.assets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxconn.iot.assets.model.EpcPrefix;
import com.foxconn.iot.assets.model.EpcPrefixCompany;


public interface EpcPrefixCompanyRelationDao {
	
	int insert(@Param("relations") List<EpcPrefixCompany> relations);
	
	List<EpcPrefix> queryPrefixes(@Param("companyId") Long companyId);
}
