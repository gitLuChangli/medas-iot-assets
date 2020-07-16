package com.foxconn.iot.assets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public CommonResult<?> query(@PathVariable(value = "id") Long companyId, 
									@RequestParam(value = "start", required = false) String start, 
									@RequestParam(value = "end", required = false) String end, 
									@RequestParam(value = "keyword", required = false) String keyword, 
									@PageableDefault Pageable pageable) {
		Page<WorkOrder> wos = workOrderService.query(companyId, start, end, keyword, pageable);
		return CommonResult.success(CommonPage.restPage(wos));
	}
	
	@ApiOperation(value = "分配盤點人")
	@PutMapping(value = "/set/worker/{id:\\d+}")
	public CommonResult<?> setWorker(@PathVariable(value = "id") Long id, @RequestParam(value = "usernames") List<String> usernames) {
		long count = workOrderService.setWorkers(id, usernames);
		if (count > 0) {
			return CommonResult.success(null);
		} else {
			return CommonResult.failed();
		}
	}
	
	@ApiOperation(value = "結單")
	@PutMapping(value = "/complete/{id:\\d+}")
	public CommonResult<?> complete(@PathVariable(value = "id") Long id, @RequestParam(value = "note") String note) {
		long count = workOrderService.complete(id, note);
		if (count > 0) {
			return CommonResult.success(null);
		} else {
			return CommonResult.failed();
		}
	}
	
	@ApiOperation(value = "刪除工單")
	@DeleteMapping(value = "/{id:\\d+}")
	public CommonResult<?> delete(@PathVariable(value = "id") Long id) {
		long count = workOrderService.delete(id);
		if (count > 0) {
			return CommonResult.success(null);
		} else {
			return CommonResult.failed();
		}
	}
	
	@ApiOperation(value = "查詢用戶盤點工單")
	@GetMapping(value = "/username/{username}")
	public CommonResult<?> queryByUsername(@PathVariable(value = "username") String username, 
								@RequestParam(value = "start", required = false) String start, 
								@RequestParam(value = "end", required = false) String end, 
								@PageableDefault Pageable pageable) {
		Page<WorkOrder> wos = workOrderService.queryByUsername(username, start, end, pageable);
		return CommonResult.success(CommonPage.restPage(wos));
	}
}
