package com.foxconn.iot.assets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxconn.iot.assets.common.api.CommonPage;
import com.foxconn.iot.assets.common.api.CommonResult;
import com.foxconn.iot.assets.mongo.document.WorkOrder;
import com.foxconn.iot.assets.mongo.service.WorkOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/inventory")
@Api(tags = {"資產盤點接口"}, value = "AssetInventoryController")
public class AssetInventoryController {
	
	@Autowired
	private WorkOrderService workOrderService;
	
	@ApiOperation(value = "查詢工單")
	@GetMapping(value = "/company/{id:\\d+}")
	public CommonResult<?> query(@PathVariable(value = "id") Long companyId, @PageableDefault Pageable pageable) {
		Page<WorkOrder> wos = workOrderService.query(companyId, pageable);
		return CommonResult.success(CommonPage.restPage(wos));
	}
}
