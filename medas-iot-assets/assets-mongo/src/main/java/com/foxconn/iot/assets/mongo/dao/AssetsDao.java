package com.foxconn.iot.assets.mongo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foxconn.iot.assets.mongo.document.Asset;

public interface AssetsDao {
	
	long save(List<Asset> assets);
	
	Page<Asset> query(Long companyId, Pageable pageable);
	
	long count(Long companyId);	
	
	List<Asset> query(Long companyId, List<Long> ids);
	
	long update(long assetId, String building, String floor, String XT);
}
