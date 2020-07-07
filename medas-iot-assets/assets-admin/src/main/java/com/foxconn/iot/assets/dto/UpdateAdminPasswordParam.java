package com.foxconn.iot.assets.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 修改用户名密码参数
 */
public class UpdateAdminPasswordParam {
	
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    
    @ApiModelProperty(value = "旧密码", required = true)
    @NotEmpty(message = "旧密码不能为空")
    private String oldPassword;
    
    @ApiModelProperty(value = "新密码", required = true)
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
	
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
