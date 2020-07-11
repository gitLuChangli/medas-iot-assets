package com.foxconn.iot.assets.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxconn.iot.assets.mapper.UmsResourceCategoryMapper;
import com.foxconn.iot.assets.mapper.UmsResourceMapper;
import com.foxconn.iot.assets.model.UmsResourceCategory;
import com.foxconn.iot.assets.model.UmsResourceCategoryExample;
import com.foxconn.iot.assets.model.UmsResourceExample;
import com.foxconn.iot.assets.service.UmsResourceCategoryService;

/**
 * 后台资源分类管理Service实现类
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
	@Autowired
	private UmsResourceCategoryMapper resourceCategoryMapper;
	@Autowired
	private UmsResourceMapper resourceMapper;

	@Override
	public List<UmsResourceCategory> listAll() {
		UmsResourceCategoryExample example = new UmsResourceCategoryExample();
		example.setOrderByClause("sort asc");
		return resourceCategoryMapper.selectByExample(example);
	}

	@Override
	public int create(UmsResourceCategory umsResourceCategory) {
		umsResourceCategory.setCreateTime(new Date());
		return resourceCategoryMapper.insert(umsResourceCategory);
	}

	@Override
	public int update(Long id, UmsResourceCategory umsResourceCategory) {
		umsResourceCategory.setId(id);
		return resourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory);
	}

	@Override
	@Transactional
	public int delete(Long id) {
		UmsResourceExample example = new UmsResourceExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		resourceMapper.deleteByExample(example);
		return resourceCategoryMapper.deleteByPrimaryKey(id);
	}
}
