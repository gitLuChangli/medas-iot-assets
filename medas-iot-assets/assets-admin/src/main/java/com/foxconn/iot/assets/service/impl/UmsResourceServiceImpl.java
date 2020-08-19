package com.foxconn.iot.assets.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.mapper.UmsResourceMapper;
import com.foxconn.iot.assets.model.UmsResource;
import com.foxconn.iot.assets.model.UmsResourceExample;
import com.foxconn.iot.assets.service.UmsAdminCacheService;
import com.foxconn.iot.assets.service.UmsResourceService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

/**
 * 后台资源管理Service实现类
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
	@Autowired
	private UmsResourceMapper resourceMapper;
	@Autowired
	private UmsAdminCacheService adminCacheService;

	@Override
	public int create(UmsResource umsResource) {
		umsResource.setId(Snowflaker.getId());
		umsResource.setCreateTime(new Date());
		return resourceMapper.insert(umsResource);
	}

	@Override
	public int update(Long id, UmsResource umsResource) {
		umsResource.setId(id);
		int count = resourceMapper.updateByPrimaryKeySelective(umsResource);
		adminCacheService.delResourceListByResource(id);
		return count;
	}

	@Override
	public UmsResource getItem(Long id) {
		return resourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Long id) {
		int count = resourceMapper.deleteByPrimaryKey(id);
		adminCacheService.delResourceListByResource(id);
		return count;
	}

	@Override
	public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize,
			Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		UmsResourceExample example = new UmsResourceExample();
		UmsResourceExample.Criteria criteria = example.createCriteria();
		if (categoryId != null) {
			criteria.andCategoryIdEqualTo(categoryId);
		}
		if (StrUtil.isNotEmpty(nameKeyword)) {
			criteria.andNameLike('%' + nameKeyword + '%');
		}
		if (StrUtil.isNotEmpty(urlKeyword)) {
			criteria.andUrlLike('%' + urlKeyword + '%');
		}
		return resourceMapper.selectByExample(example);
	}

	@Override
	public List<UmsResource> listAll() {
		return resourceMapper.selectByExample(new UmsResourceExample());
	}
}
