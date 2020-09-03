package com.foxconn.iot.assets.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.foxconn.iot.assets.dto.TagPrefixDto;
import com.foxconn.iot.assets.model.TagPrefix;

public interface TagPrefixService {
	
	long create(TagPrefixDto prefix);
	
	@Transactional
	long update(TagPrefixDto prefix);
	
	List<TagPrefix> query();
	
	@Transactional
	long delete(Long id);
	
	long setCompanyPrefixes(Long companyId, List<Long> prefixIds);
	
	List<TagPrefix> queryByCompany(Long companyId);
}
