package com.foxconn.iot.assets.mongo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.foxconn.iot.assets.mongo.dao.dto.AssetEpcPrefixDto;
import com.foxconn.iot.assets.mongo.document.AssetEpcPrefix;

public interface AssetEpcPrefixService {
	
	void save(AssetEpcPrefixDto prefix);
	
	@Transactional
	Long update(AssetEpcPrefixDto prefix, Long id);
	
	@Transactional
	Long delete(Long id);
	
	List<AssetEpcPrefix> query(Long companyId);
}
