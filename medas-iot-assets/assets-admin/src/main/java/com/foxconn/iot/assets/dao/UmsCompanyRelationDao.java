package com.foxconn.iot.assets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxconn.iot.assets.model.UmsCompanyRelation;
import com.foxconn.iot.assets.model.UmsCompanyRelationVo;

public interface UmsCompanyRelationDao {

	List<Long> getAncestors(long descendant);
	
	int insert(@Param("list") List<UmsCompanyRelation> relations);
	
	List<UmsCompanyRelationVo> query();
	
	
}
