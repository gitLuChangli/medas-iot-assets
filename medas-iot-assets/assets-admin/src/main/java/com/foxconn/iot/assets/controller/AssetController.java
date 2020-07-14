package com.foxconn.iot.assets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxconn.iot.assets.common.api.CommonPage;
import com.foxconn.iot.assets.common.api.CommonResult;
import com.foxconn.iot.assets.dto.AssetsParam;
import com.foxconn.iot.assets.mongo.document.Asset;
import com.foxconn.iot.assets.mongo.service.AssetsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/asset")
@Api(tags = {"	資產管理接口"}, value="AssetController")
public class AssetController {
	
	@Autowired
	private AssetsService assetsService;
	
	@ApiOperation(value = "錄入固定資產")
	@PostMapping(value = "/")	
	public CommonResult<?> save(@RequestBody AssetsParam assets, BindingResult result) {
		assetsService.save(assets.getCompanyId(), assets.getAssets());
		return CommonResult.success(null);
	}
	
	@ApiOperation(value = "查詢固定資產")
	@GetMapping(value = "/company/{id:\\d+}")
	public CommonResult<CommonPage<Asset>> query(@PathVariable(value = "id") Long companyId, @PageableDefault Pageable pageable) {
		Page<Asset> assets = assetsService.query(companyId, pageable);
		return CommonResult.success(CommonPage.restPage(assets));
	}
}
