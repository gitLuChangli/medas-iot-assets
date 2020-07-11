package com.foxconn.iot.assets.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.dao.UmsCompanyRelationDao;
import com.foxconn.iot.assets.dto.UmsCompanyDto;
import com.foxconn.iot.assets.mapper.UmsCompanyMapper;
import com.foxconn.iot.assets.mapper.UmsCompanyRelationMapper;
import com.foxconn.iot.assets.model.UmsCompany;
import com.foxconn.iot.assets.model.UmsCompanyExample;
import com.foxconn.iot.assets.model.UmsCompanyRelation;
import com.foxconn.iot.assets.service.UmsCompanyService;

@Service
public class UmsCompanyServiceImpl implements UmsCompanyService {

	@Autowired
	private UmsCompanyMapper companyMapper;
	@Autowired
	private UmsCompanyRelationMapper companyRelationMapper;
	@Autowired
	private UmsCompanyRelationDao companyRelationDao;
	
	@Override
	public int create(UmsCompanyDto company) {
		UmsCompany umsCompany = new UmsCompany();
		BeanUtils.copyProperties(company, umsCompany);		
		Long id = Snowflaker.getId();
		umsCompany.setId(id);
		umsCompany.setCreateTime(new Date());
		
		List<UmsCompanyRelation> relations = new ArrayList<>();
		UmsCompanyRelation self = new UmsCompanyRelation();
		self.setId(Snowflaker.getId());
		self.setAncestor(id);
		self.setDescendant(id);
		self.setDepth(0);
		relations.add(self);
		if (company.getAncestorIds() != null && company.getAncestorIds().size() > 0) {
			int length = company.getAncestorIds().size();
			long descendant = company.getAncestorIds().get(length - 1);
			List<Long> ancestors = companyRelationDao.getAncestors(descendant);
			ancestors.removeAll(company.getAncestorIds());
			/** 所傳部門層級關係正確 */
			if (ancestors.size() == 0) {
				
				for (int i = 0; i < length; i++) {
					UmsCompanyRelation relation = new UmsCompanyRelation();
					relation.setId(Snowflaker.getId());
					relation.setDescendant(id);
					relation.setAncestor(ancestors.get(i));
					relation.setDepth(length - i);
					relations.add(relation);
				}
			}
		}
		companyRelationDao.insert(relations);
		return companyMapper.insert(umsCompany);
	}

	@Override
	public int update(Long id, UmsCompanyDto company) {
		return 0;
	}

	@Override
	public int delete(List<Long> ids) {
		return 0;
	}

	@Override
	public List<UmsCompanyDto> list() {
		return null;
	}

	@Override
	public int updateStatus(Long id, int status) {
		return 0;
	}

}
