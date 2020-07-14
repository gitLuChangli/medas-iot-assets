package com.foxconn.iot.assets.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.mongo.dao.AssetsDao;
import com.foxconn.iot.assets.mongo.document.Asset;
import com.foxconn.iot.assets.mongo.service.AssetsService;

@Service
public class AssetsServiceImpl implements AssetsService {
	
	@Autowired
	private AssetsDao assetDao;

	@Override
	public void save(Long companyId, List<Asset> assets) {
		for (Asset asset : assets) {
			asset.setId(Snowflaker.getId());
			asset.setCompanyId(companyId);
		}
		assetDao.save(assets);
	}

	@Override
	public Page<Asset> query(Long companyId, Pageable pageable) {
		return assetDao.query(companyId, pageable);
	}
}
