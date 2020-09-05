package com.foxconn.iot.assets.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxconn.iot.assets.common.api.CommonResult;
import com.foxconn.iot.assets.component.IAuthenticationFacade;
import com.foxconn.iot.assets.dto.EpcPrefixDto;
import com.foxconn.iot.assets.model.EpcPrefix;
import com.foxconn.iot.assets.service.EpcPrefixService;

import cn.hutool.core.codec.Base64;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "电子标签编码前缀接口" }, value = "EpcPrefixControl")
public class EpcPrefixControl {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private EpcPrefixService epcPrefixService;
	@Autowired
	private IAuthenticationFacade authenticationFacade;

	public CommonResult<?> createPrefix(EpcPrefixDto prefix) {
		long result = epcPrefixService.create(prefix);
		if (result > 0) {
			return CommonResult.success(null);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation(value = "新增前缀码")
	@PostMapping(value = "/api/admin/epc/prefix")
	public CommonResult<?> create(@Valid @RequestBody EpcPrefixDto prefix, BindingResult result) {
		return createPrefix(prefix);
	}

	@ApiOperation(value = "获取所有前缀码")
	@GetMapping(value = "/api/admin/epc/prefix")
	public CommonResult<?> queryAll() {
		List<EpcPrefix> prefixes = epcPrefixService.query();
		return CommonResult.success(prefixes);
	}

	@ApiOperation(value = "删除前缀码")
	@DeleteMapping(value = "/api/admin/epc/prefix/{id:\\d+}")
	public CommonResult<?> delete(@PathVariable(value = "id", required = true) Long id) {
		long result = epcPrefixService.delete(id);
		if (result > 0)
			return CommonResult.success(null);
		else
			return CommonResult.failed();
	}

	@ApiOperation(value = "修改前缀码")
	@PutMapping(value = "/api/admin/epc/prefix")
	public CommonResult<?> update(@Valid @RequestBody EpcPrefixDto prefix, BindingResult result) {
		long r = epcPrefixService.update(prefix);
		if (r > 0)
			return CommonResult.success(null);
		else
			return CommonResult.failed();
	}

	@ApiOperation(value = "为部门设置前缀码")
	@PostMapping(value = "/api/admin/epc/prefix/company/{id:\\d+}")
	public CommonResult<?> setCompanyPrefixes(@PathVariable(value = "id") Long companyId,
			@RequestParam("prefixIds") List<Long> prefixIds) {
		long result = epcPrefixService.setCompanyPrefixes(companyId, prefixIds);
		if (result > 0)
			return CommonResult.success(null);
		else
			return CommonResult.failed();
	}

	@ApiOperation(value = "查看部门前缀码")
	@GetMapping(value = "/api/admin/epc/prefix/company/{id:\\d+}")
	public CommonResult<?> queryByCompany(@PathVariable(value = "id") Long companyId) {
		List<EpcPrefix> prefixes = epcPrefixService.queryByCompany(companyId);
		return CommonResult.success(prefixes);
	}

	@ApiOperation(value = "用户查看所在部门所属前缀码")
	@GetMapping(value = "/api/epc/prefix")
	public CommonResult<?> queryBySelf() {
		long companyId = authenticationFacade.getCompanyId();
		List<EpcPrefix> prefixes = epcPrefixService.queryByCompany(companyId);
		Map<String, String> map = new HashedMap<>();
		for (EpcPrefix prefix : prefixes) {
			map.put(prefix.getK(), prefix.getV());
		}
		return CommonResult.success(map);
	}

	@ApiOperation(value = "下载前缀码")
	@GetMapping(value = "/api/admin/epc/prefix/download/company/{id:\\d+}")
	public void download(HttpServletResponse response, @PathVariable(value = "id") Long companyId)
			throws JsonProcessingException, IOException {
		List<EpcPrefix> prefixes = epcPrefixService.queryByCompany(companyId);
		Map<String, String> map = new HashedMap<>();
		for (EpcPrefix prefix : prefixes) {
			map.put(prefix.getK(), prefix.getV());
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + companyId + ".txt");
		response.getWriter().write(Base64.encode(objectMapper.writeValueAsString(map)));
	}
}
