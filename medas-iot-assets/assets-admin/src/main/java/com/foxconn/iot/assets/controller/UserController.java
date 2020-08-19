package com.foxconn.iot.assets.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxconn.iot.assets.common.api.CommonPage;
import com.foxconn.iot.assets.common.api.CommonResult;
import com.foxconn.iot.assets.dto.UmsAdminDto;
import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.model.UmsRole;
import com.foxconn.iot.assets.service.UmsAdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台用户接口
 */
@RestController
@Api(tags = "后台用户接口", value = "UserController")
@RequestMapping("/api/admin/user")
public class UserController {
	
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminService adminService;

    @ApiOperation(value = "创建用户")
    @PostMapping(value = "/")
    public CommonResult<?> create(@Valid @RequestBody UmsAdminDto admin, BindingResult result) {
    	int count = adminService.create(admin);
    	if (count > 0) {
    		return CommonResult.success(null);
    	} else {
    		return CommonResult.failed();
    	}
    }
    
    @ApiOperation(value = "修改用戶")
    @PutMapping(value = "/{id:\\d+}")
    public CommonResult<?> update(@PathVariable(value = "id") Long id, @RequestBody UmsAdminDto admin) {
    	int count = adminService.update(id, admin);
    	if (count > 0) {
    		return CommonResult.success(null);
    	} else {
    		return CommonResult.failed();
    	}
    }
    
    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/")
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
    												@RequestParam(value = "companyId", required = false) Long companyId,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(keyword, companyId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }
    
    @ApiOperation(value = "启用、禁用用戶")
    @PutMapping(value = "/disable/{id:\\d+}/{status:^[01]$}")
    public CommonResult<?> disable(@PathVariable(value = "id") Long id, @PathVariable(value = "status") Integer status) {
    	int count = adminService.disable(id, status);
    	if (count > 0) {
    		return CommonResult.success(null);
    	} else {
    		return CommonResult.failed();
    	}
    }

    @ApiOperation("删除指定用户信息")
    @DeleteMapping(value = "/{id:\\d+}")
    public CommonResult<?> delete(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/role/{id:\\d+}")
    public CommonResult<?> updateRole(@PathVariable("id") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping(value = "/role/{adminId}")
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        if (roleList.size() > 0 && roleList.get(0) != null) {
        	return CommonResult.success(roleList);
        } else {
        	return CommonResult.success(null);
        }
    }   
    
    @ApiOperation(value = "获取用户所在部门的层级关系")
    @GetMapping(value = "/company/relation/{id:\\d+}")
    public CommonResult<?> queryCompanyRelation(@PathVariable(value = "id") Long userid) {
    	List<Long> companyIds = adminService.queryCompanyRelation(userid);
    	List<String> companyIds_ = new ArrayList<>();
    	for (Long id : companyIds) {
    		companyIds_.add(id + "");
    	}
    	return CommonResult.success(companyIds_);
    }

    @ApiOperation(value = "重置密码")
    @PutMapping(value = "/reset/password/{id:\\d+}")
    public CommonResult<?> resetPassword(@PathVariable(value = "id") Long userid) {
    	int count = adminService.resetPassword(userid);
    	if (count > 0) {
    		return CommonResult.success(null);
    	} else {
    		return CommonResult.failed();
    	}
    }
}
