package com.foxconn.iot.assets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxconn.iot.assets.model.AimsAssets;

public interface AImsAssetsDao {
	
	int insert(@Param("list") List<AimsAssets> assets);
}
