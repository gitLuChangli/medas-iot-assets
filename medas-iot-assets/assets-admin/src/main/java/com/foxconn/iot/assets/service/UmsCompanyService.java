package com.foxconn.iot.assets.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.foxconn.iot.assets.dto.UmsCompanyDto;

public interface UmsCompanyService {
	
	@Transactional
	int create(UmsCompanyDto company);
	
	@Transactional
	int update(Long id, UmsCompanyDto company);
	
	@Transactional
	int delete(Long id);
	
	List<UmsCompanyDto> list();
	
	@Transactional
	int updateStatus(Long id, int status);
}
