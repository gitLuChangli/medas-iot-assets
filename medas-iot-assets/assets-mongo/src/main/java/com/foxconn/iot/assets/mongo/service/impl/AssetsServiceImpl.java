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
	public long save(Long companyId, List<Asset> assets) {
		for (Asset asset : assets) {
			asset.setId(Snowflaker.getId());
			asset.setCompanyId(companyId);
		}
		int each = 64;
		int size = assets.size();
		int times = size / each;
		int last = size % each;
		if (last > 0) times++;		
		int count = 0;
		for (int i = 0; i < times; i++) {
			if (i < times - 1) {
				count += assetDao.save(assets.subList(i * each, i * each + each));
			} else {
				count += assetDao.save(assets.subList(i * each, i * each + last));
			}
		}
		return count;
	}

	@Override
	public Page<Asset> query(Long companyId, Pageable pageable) {
		return assetDao.query(companyId, pageable);
	}

	@Override
	public List<Asset> query(Long companyId, List<Long> assetIds) {
		return assetDao.query(companyId, assetIds);
	}
}
