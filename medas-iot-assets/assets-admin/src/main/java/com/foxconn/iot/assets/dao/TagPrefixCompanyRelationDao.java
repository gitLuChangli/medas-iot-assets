package com.foxconn.iot.assets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxconn.iot.assets.model.TagPrefix;
import com.foxconn.iot.assets.model.TagPrefixCompany;


public interface TagPrefixCompanyRelationDao {
	
	int insert(@Param("relations") List<TagPrefixCompany> relations);
	
	List<TagPrefix> queryPrefixes(@Param("companyId") Long companyId);
}
