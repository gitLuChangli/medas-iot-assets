package com.foxconn.iot.assets.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用戶登錄參數
 */
public class UmsAdminLoginParam {
	
	@ApiModelProperty(value = "用戶名", required = true)
	@NotEmpty(message = "用戶名不能為空")
	private String username;
	
	@ApiModelProperty(value = "密碼", required = true)
	@NotEmpty(message = "密碼不能為空")
	private String password;
	
	@ApiModelProperty(value = "驗證碼")
	private String verifyCode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
