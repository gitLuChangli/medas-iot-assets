package com.foxconn.iot.assets.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.dao.EpcPrefixCompanyRelationDao;
import com.foxconn.iot.assets.dto.EpcPrefixDto;
import com.foxconn.iot.assets.mapper.EpcPrefixCompanyMapper;
import com.foxconn.iot.assets.mapper.EpcPrefixMapper;
import com.foxconn.iot.assets.mapper.UmsCompanyMapper;
import com.foxconn.iot.assets.model.EpcPrefix;
import com.foxconn.iot.assets.model.EpcPrefixCompany;
import com.foxconn.iot.assets.model.EpcPrefixCompanyExample;
import com.foxconn.iot.assets.model.EpcPrefixExample;
import com.foxconn.iot.assets.model.UmsCompany;
import com.foxconn.iot.assets.service.EpcPrefixService;

@Service
public class EpcPrefixServiceImpl implements EpcPrefixService {

	@Autowired
	private EpcPrefixMapper prefixMapper;
	@Autowired
	private UmsCompanyMapper companyMapper;
	@Autowired
	private EpcPrefixCompanyMapper prefixCompanyMapper;
	@Autowired
	private EpcPrefixCompanyRelationDao prefixCompanyDao;
	
	@Override
	public long create(EpcPrefixDto prefix) {
		prefix.setId(Snowflaker.getId());
		EpcPrefix epcPrefix = new EpcPrefix();
		BeanUtils.copyProperties(prefix, epcPrefix);
		return prefixMapper.insert(epcPrefix);
	}

	@Override
	public long update(EpcPrefixDto prefix) {
		EpcPrefix epcPrefix = new EpcPrefix();
		BeanUtils.copyProperties(prefix, epcPrefix);
		return prefixMapper.updateByPrimaryKeySelective(epcPrefix);
	}

	@Override
	public List<EpcPrefix> query() {		
		return prefixMapper.selectByExample(new EpcPrefixExample());
	}

	@Override
	public long delete(Long id) {
		EpcPrefixCompanyExample example = new EpcPrefixCompanyExample();
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
		
		// 如果没有传入前缀编号，直接置空
		if (prefixIds == null || prefixIds.size() == 0) {
			EpcPrefixCompanyExample delete = new EpcPrefixCompanyExample();
			delete.createCriteria().andCompanyIdEqualTo(companyId);		
			return prefixCompanyMapper.deleteByExample(delete);
		} else {
			EpcPrefixExample example = new EpcPrefixExample();
			example.createCriteria().andIdIn(prefixIds);
			List<EpcPrefix> prefixes = prefixMapper.selectByExample(example);
			
			// 传入的前缀编号是无效的，返回错误
			if (prefixes == null || prefixes.size() == 0) return 0;
			
			EpcPrefixCompanyExample delete = new EpcPrefixCompanyExample();
			delete.createCriteria().andCompanyIdEqualTo(companyId);		
			prefixCompanyMapper.deleteByExample(delete);
			
			List<EpcPrefixCompany> tpcs = new ArrayList<>();
			
			for (EpcPrefix prefix : prefixes) {
				EpcPrefixCompany tpc = new EpcPrefixCompany();
				tpc.setId(Snowflaker.getId());
				tpc.setCompanyId(companyId);
				tpc.setPrefixId(prefix.getId());
				tpcs.add(tpc);
			}
			
			return prefixCompanyDao.insert(tpcs);
		}
	}

	@Override
	public List<EpcPrefix> queryByCompany(Long companyId) {
		return prefixCompanyDao.queryPrefixes(companyId);
	}
}
