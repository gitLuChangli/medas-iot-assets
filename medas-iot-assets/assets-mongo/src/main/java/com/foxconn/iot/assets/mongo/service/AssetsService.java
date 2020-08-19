package com.foxconn.iot.assets.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foxconn.iot.assets.mongo.document.Asset;

public interface AssetsService {
	
	long save(Long companyId, List<Asset> assets);
	
	Page<Asset> query(Long companyId, Pageable pageable, String keyword);
	
	List<Asset> query(Long companyId, List<Long> assetIds);
}
