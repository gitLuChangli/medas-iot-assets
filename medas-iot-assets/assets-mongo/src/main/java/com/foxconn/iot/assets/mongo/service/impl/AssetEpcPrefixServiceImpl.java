package com.foxconn.iot.assets.mongo.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.mongo.dao.AssetEpcPrefixDao;
import com.foxconn.iot.assets.mongo.dao.dto.AssetEpcPrefixDto;
import com.foxconn.iot.assets.mongo.document.AssetEpcPrefix;
import com.foxconn.iot.assets.mongo.service.AssetEpcPrefixService;

@Service
public class AssetEpcPrefixServiceImpl implements AssetEpcPrefixService {

	@Autowired
	private AssetEpcPrefixDao assetPrefixDao;
	
	@Override
	public void save(AssetEpcPrefixDto prefix) {
		AssetEpcPrefix entity = new AssetEpcPrefix();
		BeanUtils.copyProperties(prefix, entity);
		entity.setId(Snowflaker.getId());
		assetPrefixDao.save(entity);
	}
	
	@Override
	public Long update(AssetEpcPrefixDto prefix, Long id) {
		AssetEpcPrefix entity = new AssetEpcPrefix();
		BeanUtils.copyProperties(prefix, entity);
		entity.setId(id);
		return assetPrefixDao.update(entity);
	}

	@Override
	public Long delete(Long id) {
		return assetPrefixDao.delete(id);
	}

	@Override
	public List<AssetEpcPrefix> query(Long companyId) {
		return assetPrefixDao.query(companyId);
	}
}
