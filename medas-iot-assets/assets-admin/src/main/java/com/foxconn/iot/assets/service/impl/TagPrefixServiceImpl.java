package com.foxconn.iot.assets.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.dao.TagPrefixCompanyRelationDao;
import com.foxconn.iot.assets.dto.TagPrefixDto;
import com.foxconn.iot.assets.mapper.TagPrefixCompanyMapper;
import com.foxconn.iot.assets.mapper.TagPrefixMapper;
import com.foxconn.iot.assets.mapper.UmsCompanyMapper;
import com.foxconn.iot.assets.model.TagPrefix;
import com.foxconn.iot.assets.model.TagPrefixCompany;
import com.foxconn.iot.assets.model.TagPrefixCompanyExample;
import com.foxconn.iot.assets.model.TagPrefixExample;
import com.foxconn.iot.assets.model.UmsCompany;
import com.foxconn.iot.assets.service.TagPrefixService;

@Service
public class TagPrefixServiceImpl implements TagPrefixService {

	@Autowired
	private TagPrefixMapper prefixMapper;
	@Autowired
	private UmsCompanyMapper companyMapper;
	@Autowired
	private TagPrefixCompanyMapper prefixCompanyMapper;
	@Autowired
	private TagPrefixCompanyRelationDao prefixCompanyDao;
	
	@Override
	public long create(TagPrefixDto prefix) {
		prefix.setId(Snowflaker.getId());
		TagPrefix tagPrefix = new TagPrefix();
		BeanUtils.copyProperties(prefix, tagPrefix);
		return prefixMapper.insert(tagPrefix);
	}

	@Override
	public long update(TagPrefixDto prefix) {
		TagPrefix tagPrefix = new TagPrefix();
		BeanUtils.copyProperties(prefix, tagPrefix);
		return prefixMapper.updateByPrimaryKeySelective(tagPrefix);
	}

	@Override
	public List<TagPrefix> query() {		
		return prefixMapper.selectByExample(new TagPrefixExample());
	}

	@Override
	public long delete(Long id) {
		TagPrefixCompanyExample example = new TagPrefixCompanyExample();
		example.createCriteria().andPrefixIdEqualTo(id);
		prefixCompanyMapper.deleteByExample(example);
		return prefixMapper.deleteByPrimaryKey(id);
	}

	@Override
	public long setCompanyPrefixes(Long companyId, List<Long> prefixIds) {
		UmsCompany company = companyMapper.selectByPrimaryKey(companyId);
		if (company == null) {
			return 0;
		}
		
		TagPrefixExample example = new TagPrefixExample();
		example.createCriteria().andIdIn(prefixIds);
		List<TagPrefix> prefixes = prefixMapper.selectByExample(example);
		
		TagPrefixCompanyExample delete = new TagPrefixCompanyExample();
		delete.createCriteria().andCompanyIdEqualTo(companyId);		
		prefixCompanyMapper.deleteByExample(delete);
		
		List<TagPrefixCompany> tpcs = new ArrayList<>();
		
		for (TagPrefix prefix : prefixes) {
			TagPrefixCompany tpc = new TagPrefixCompany();
			tpc.setId(Snowflaker.getId());
			tpc.setCompanyId(companyId);
			tpc.setPrefixId(prefix.getId());
			tpcs.add(tpc);
		}
		
		return prefixCompanyDao.insert(tpcs);		
	}

	@Override
	public List<TagPrefix> queryByCompany(Long companyId) {
		return prefixCompanyDao.queryPrefixes(companyId);
	}
}
