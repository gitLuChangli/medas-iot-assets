package com.foxconn.iot.assets.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.foxconn.iot.assets.dto.EpcPrefixDto;
import com.foxconn.iot.assets.model.EpcPrefix;

public interface EpcPrefixService {
	
	long create(EpcPrefixDto prefix);
	
	@Transactional
	long update(EpcPrefixDto prefix);
	
	List<EpcPrefix> query();
	
	@Transactional
	long delete(Long id);
	
	long setCompanyPrefixes(Long companyId, List<Long> prefixIds);
	
	List<EpcPrefix> queryByCompany(Long companyId);
}
