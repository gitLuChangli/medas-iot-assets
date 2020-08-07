package com.foxconn.iot.assets.mongo.dao;

import java.util.List;

import com.foxconn.iot.assets.mongo.document.AssetEpcPrefix;

public interface AssetEpcPrefixDao {
	
	void save(AssetEpcPrefix prefix);
	
	Long update(AssetEpcPrefix prefix);
	
	long delete(Long id);
	
	List<AssetEpcPrefix> query(Long companyId);
}
