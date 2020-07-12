package com.foxconn.iot.assets.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.foxconn.iot.assets.common.api.CommonResult;
import com.foxconn.iot.assets.dto.UmsCompanyDto;
import com.foxconn.iot.assets.service.UmsCompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/company")
@Api(tags = {"部门管理接口"}, value="UmsCompanyController")
public class UmsCompanyController {

	@Autowired
	private UmsCompanyService companyService;
	
	@ApiOperation(value = "創建部門")
	@PostMapping(value = "/")
	public CommonResult<?> create(@Valid @JsonView(UmsCompanyDto.Create.class) @RequestBody UmsCompanyDto company, BindingResult result) {
		int count = companyService.create(company);
		if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
	}
	
	@ApiOperation(value = "修改部門信息")
	@PutMapping(value = "/{id:\\d+}")
	public CommonResult<?> update(@PathVariable(value = "id") Long id, @RequestBody UmsCompanyDto company) {
		int count = companyService.update(id, company);
		if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
	}
	
	@ApiOperation(value = "獲取部門信息")
	@GetMapping(value = "/")
	public CommonResult<?> query() {
		List<UmsCompanyDto> companies = companyService.list();
		return CommonResult.success(companies);
	}
	
	@ApiOperation(value = "刪除部門")
	@DeleteMapping(value = "/{id:\\d+}")
	public CommonResult<?> delete(@PathVariable(value = "id") Long id) {
		int count = companyService.delete(id);
		if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
	}
	
	@ApiOperation(value = "啓用/禁用部門")
	@PutMapping(value = "/disable/{id:\\d+}/{status:^[01]$}")
	public CommonResult<?> disable(@PathVariable(value = "id") Long id, @PathVariable(value = "status") Integer status) {
		int count = companyService.updateStatus(id, status);
		if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
	}
}
