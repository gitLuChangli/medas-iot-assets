package com.foxconn.iot.assets.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonView;
import com.foxconn.iot.assets.bo.AdminUserDetails;
import com.foxconn.iot.assets.common.api.CommonPage;
import com.foxconn.iot.assets.common.api.CommonResult;
import com.foxconn.iot.assets.common.api.VerificationCode;
import com.foxconn.iot.assets.component.IAuthenticationFacade;
import com.foxconn.iot.assets.dto.ModifyMyself;
import com.foxconn.iot.assets.dto.UmsAdminLoginParam;
import com.foxconn.iot.assets.dto.UmsMenuNode;
import com.foxconn.iot.assets.model.UmsAdminLoginLog;
import com.foxconn.iot.assets.model.UmsAdminVo;
import com.foxconn.iot.assets.service.UmsAdminService;
import com.foxconn.iot.assets.service.UmsMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "登录模块" }, value = "LoginController")
public class LoginController {

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private UmsAdminService adminService;
	@Autowired
	private UmsMenuService menuService;
	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@ApiOperation(value = "获取验证码")
	@GetMapping("/vcode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", required = true) String username) throws IOException {		
		String text = adminService.getVerifyCode(username);
		if (StringUtils.isEmpty(text)) {
			text = VerificationCode.getCode(4);
			adminService.setVerifyCode(username, text);
		}
		BufferedImage image = VerificationCode.getImage(text);
		VerificationCode.output(image, response.getOutputStream());
	}

	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CommonResult<?> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
		if (!adminService.checkVerifyCode(umsAdminLoginParam)) {
			return CommonResult.verifyCodeError();
		}
		String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
		if (token == null) {
			return CommonResult.validateFailed("用戶名或密碼錯誤");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return CommonResult.success(tokenMap);
	}

	@ApiOperation(value = "刷新token")
	@RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
	public CommonResult<?> refreshToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String refreshToken = adminService.refreshToken(token);
		if (refreshToken == null) {
			return CommonResult.failed("token已经过期！");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", refreshToken);
		tokenMap.put("tokenHead", tokenHead);
		return CommonResult.success(tokenMap);
	}

	@ApiOperation(value = "登出")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public CommonResult<?> logout() {
		return CommonResult.success(null);
	}
	
	@ApiOperation(value = "获取个人信息")
	@RequestMapping(value = "/api/mine", method = RequestMethod.GET)
	public CommonResult<?> mineInformation(Principal principal) {
		String username = principal.getName();
		UmsAdminVo admin = adminService.queryInfo(username);		
        return CommonResult.success(admin);
	}
	
	@ApiOperation(value = "获取个人菜单")
	@GetMapping(value = "/api/mine/menus")
	public CommonResult<?> mineMenus(Principal principal) {
		String username = principal.getName();
		List<UmsMenuNode> menus = menuService.treeList(username);
		return CommonResult.success(menus);
	}
	
	@ApiOperation(value = "修改个人密码")
	@PutMapping(value = "/api/mine/password")
	public CommonResult<?> modifySelfPassword(@RequestBody @JsonView(ModifyMyself.Password.class) ModifyMyself myself, BindingResult result) {
		Authentication authentication = authenticationFacade.getAuthentication();
		AdminUserDetails user = (AdminUserDetails) authentication.getPrincipal();
		int status = adminService.updatePassword(user.getUsername(), myself.getPassword(), myself.getNewpwd());
		if (status == -1) {
			return CommonResult.failed("密码错误");
		} else if (status > 0) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
	
	@ApiOperation(value = "修改个人信息")
	@PutMapping(value = "/api/mine/information")
	public CommonResult<?> modifyMyselfInformation(@RequestBody @JsonView(ModifyMyself.Information.class) ModifyMyself myself, BindingResult result) {
		int status = adminService.updateInformation(authenticationFacade.getUserId(), myself.getEmail(), myself.getPhone(), myself.getExt());
		if (status > 0) return CommonResult.success(null);
		else return CommonResult.failed();
	}
	
	@ApiOperation(value = "查看个人操作记录")
	@GetMapping(value = "/api/mine/operation")
	public CommonResult<?> mineOperation(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsAdminLoginLog> logs = adminService.listLoginLog(authenticationFacade.getUserId(), pageSize, pageNum);
		return CommonResult.success(CommonPage.restPage(logs));
	}
}
