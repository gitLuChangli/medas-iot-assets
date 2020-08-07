package com.foxconn.iot.assets.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxconn.iot.assets.common.api.CommonResult;
import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.mongo.dao.dto.AssetEpcPrefixDto;
import com.foxconn.iot.assets.mongo.document.AssetEpcPrefix;
import com.foxconn.iot.assets.mongo.service.AssetEpcPrefixService;
import com.foxconn.iot.assets.service.UmsAdminService;

@RestController
@RequestMapping(value = "/api/asset/epc/prefix")
public class UmsAssetEpcPrefixController {
	
	@Autowired
	private AssetEpcPrefixService assetEpcPrefixService;
	@Autowired
	private UmsAdminService adminService;
	
	@PostMapping(value = "/")
	public CommonResult<?> create(@Valid @RequestBody AssetEpcPrefixDto prefix, BindingResult result) {
		assetEpcPrefixService.save(prefix);
		return CommonResult.success(null);
	}
	
	@PutMapping(value = "/{id:\\d+}")
	public CommonResult<?> save(@PathVariable(value = "id") Long id, @Valid @RequestBody AssetEpcPrefixDto prefix, BindingResult result) {
		Long count = assetEpcPrefixService.update(prefix, id);
		if (count > 0) {
			return CommonResult.success(null);
		} else {
			return CommonResult.failed();
		}
	}
	
	@GetMapping(value = "/company/{id:\\d+}")
	public CommonResult<List<AssetEpcPrefix>> query(@PathVariable(value = "id") Long companyId) {
		List<AssetEpcPrefix> prefixes = assetEpcPrefixService.query(companyId);
		return CommonResult.success(prefixes);
	}
	
	@GetMapping(value = "/")
	public CommonResult<List<AssetEpcPrefix>> query(Principal principal) {
		String username = principal.getName();
		UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
		if (umsAdmin == null) {
			return CommonResult.failed();
		}
		Long companyId = umsAdmin.getCompanyId();
		List<AssetEpcPrefix> prefixes = assetEpcPrefixService.query(companyId);
		return CommonResult.success(prefixes);
	}
}
