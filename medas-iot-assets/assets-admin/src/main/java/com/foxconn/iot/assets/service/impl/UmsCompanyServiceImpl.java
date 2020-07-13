package com.foxconn.iot.assets.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.dao.UmsCompanyRelationDao;
import com.foxconn.iot.assets.dto.UmsCompanyDto;
import com.foxconn.iot.assets.mapper.UmsCompanyMapper;
import com.foxconn.iot.assets.mapper.UmsCompanyRelationMapper;
import com.foxconn.iot.assets.model.UmsCompany;
import com.foxconn.iot.assets.model.UmsCompanyRelation;
import com.foxconn.iot.assets.model.UmsCompanyRelationExample;
import com.foxconn.iot.assets.model.UmsCompanyRelationVo;
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
		/** 不管传入的层级关系是否正确（前端显示需要），只继承最小层级的关系 */
		if (company.getAncestorIds() != null && company.getAncestorIds().length > 0) {
			long descendant = Long.parseLong(company.getAncestorIds()[company.getAncestorIds().length - 1]);
			List<Long> ancestors = companyRelationDao.getAncestors(descendant);
			int length = ancestors.size();
			for (int i = 0; i < length; i++) {
				UmsCompanyRelation relation = new UmsCompanyRelation();
				relation.setId(Snowflaker.getId());
				relation.setDescendant(id);
				relation.setAncestor(ancestors.get(i));
				relation.setDepth(length - i);
				relations.add(relation);
			}
		}
		companyRelationDao.insert(relations);
		return companyMapper.insert(umsCompany);
	}

	@Override
	public int update(Long id, UmsCompanyDto company) {
		if (company.getAncestorIds() != null && company.getAncestorIds().length > 0) {
			long descendant = Long.parseLong(company.getAncestorIds()[company.getAncestorIds().length - 1]);
			List<Long> ancestors = companyRelationDao.getAncestors(descendant);
			int length = ancestors.size();
			List<UmsCompanyRelation> relations = new ArrayList<>();
			for (int i = 0; i < length; i++) {
				UmsCompanyRelation relation = new UmsCompanyRelation();
				relation.setId(Snowflaker.getId());
				relation.setDescendant(id);
				relation.setAncestor(ancestors.get(i));
				relation.setDepth(length - i);
				relations.add(relation);
			}
			UmsCompanyRelationExample example = new UmsCompanyRelationExample();
			example.createCriteria().andDescendantEqualTo(company.getId()).andDepthGreaterThan(0);
			companyRelationMapper.deleteByExample(example);
			companyRelationDao.insert(relations);
		}

		UmsCompany umsCompany = new UmsCompany();
		BeanUtils.copyProperties(company, umsCompany);
		umsCompany.setId(id);
		return companyMapper.updateByPrimaryKeySelective(umsCompany);
	}

	@Override
	public int delete(Long id) {
		UmsCompanyRelationExample example = new UmsCompanyRelationExample();
		example.or().andDescendantEqualTo(id);
		example.or().andAncestorEqualTo(id);
		companyRelationMapper.deleteByExample(example);
		return companyMapper.deleteByPrimaryKey(id);
	}
	
	/** 计算层级关系，上级关系 */
	private Map<String, List<String>> generateCompanyRelations() {
		List<UmsCompanyRelation> relations = companyRelationDao.queryRelation();
		String companyId;
		Map<String, List<String>> relationsMap = new HashMap<String, List<String>>();
		for (UmsCompanyRelation relation : relations) {
			companyId = relation.getDescendant() + "";
			if (relation.getDepth() == 0)
				continue;
			if (relationsMap.containsKey(companyId)) {
				relationsMap.get(companyId).add(relation.getAncestor() + "");
			} else {
				List<String> ids = new LinkedList<String>();
				ids.add(relation.getAncestor() + "");
				relationsMap.put(companyId, ids);
			}
		}
		return relationsMap;
	}

	private List<UmsCompanyDto> sort(List<UmsCompanyRelationVo> companies) {
		Map<String, List<String>> relations = generateCompanyRelations();
		
		List<UmsCompanyDto> dtos = new ArrayList<>();
		/** 缓存自身的序号 */
		List<Long> indexes = new LinkedList<>();
		/** 缓存是否为根节点 */
		List<Boolean> rootIndexes = new LinkedList<>();
		/** 时候实锤为字节点 */
		List<Boolean> realDescendants = new LinkedList<>();
		List<UmsCompanyDto> selfs = new ArrayList<>();
		for (UmsCompanyRelationVo company : companies) {
			int index = indexes.indexOf(company.getId());
			if (index == -1) {
				indexes.add(company.getId());

				UmsCompanyDto dto = new UmsCompanyDto();
				BeanUtils.copyProperties(company, dto);
				selfs.add(dto);
				if (relations.containsKey(company.getId() + "")) {					
					dto.setAncestorIds(relations.get(company.getId() + "").toArray(new String[relations.get(company.getId() + "").size()]));
				}
				rootIndexes.add(true);
				realDescendants.add(false);
			} else {
				rootIndexes.set(index, false);
				int ancestorIndex = indexes.indexOf(company.getAncestor());
				if (ancestorIndex > -1) {
					/** 继承父类的层级关系 */
					if (selfs.get(ancestorIndex).getDescendants() == null) {
						List<UmsCompanyDto> dtos_ = new ArrayList<>();
						dtos_.add(selfs.get(index));
						selfs.get(ancestorIndex).setDescendants(dtos_);
					} else {
						if (!realDescendants.get(index)) {
							selfs.get(ancestorIndex).getDescendants().add(selfs.get(index));
						}
					}
					realDescendants.set(index, true);
					if (selfs.get(ancestorIndex).getAncestorIds() != null) {
						int length = selfs.get(ancestorIndex).getAncestorIds().length;
						String[] ids = new String[length + 1];
						ids = Arrays.copyOf(selfs.get(ancestorIndex).getAncestorIds(), length + 1);
						ids[length] = company.getAncestor() + "";
						selfs.get(index).setAncestorIds(ids);
					}
				}
			}
		}
		for (int i = 0; i < rootIndexes.size(); i++) {
			if (rootIndexes.get(i)) {
				dtos.add(selfs.get(i));
			}
		}
		return dtos;
	}

	@Override
	public List<UmsCompanyDto> list() {
		List<UmsCompanyRelationVo> companies = companyRelationDao.query();
		return sort(companies);
	}

	@Override
	public int updateStatus(Long id, int status) {
		UmsCompany company = new UmsCompany();
		company.setId(id);
		company.setStatus(status);
		return companyMapper.updateByPrimaryKeySelective(company);
	}

}
