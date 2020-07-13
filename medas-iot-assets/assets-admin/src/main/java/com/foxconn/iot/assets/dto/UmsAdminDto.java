package com.foxconn.iot.assets.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class UmsAdminDto {
	
	@ApiModelProperty(value = "工號用户名", required = true)
	@NotEmpty(message = "工號不能為空")
	private String username;
		
	@ApiModelProperty(value = "姓名")
	@NotEmpty(message = "姓名不能為空")
	private String nickname;
	
	@ApiModelProperty(value = "郵箱", required = true)
	@Email(message = "郵箱格式不正確")
	private String email;
	
	@ApiModelProperty(value = "手機")
	private String phone;
	
	@ApiModelProperty(value = "分機")
	private String ext;
	
	@ApiModelProperty(value = "用戶頭像")
	private String icon;
	
	@ApiModelProperty(value = "備註")
	private String note;
		
	@ApiModelProperty(value = "部門關係", required = true)
	@NotNull(message = "部門關閉不能為空")
	private String[] companyIds;
	
	@ApiModelProperty(value = "用戶狀態")
	private int status;

	public String getUsername() {
		return username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String[] getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(String[] companyIds) {
		this.companyIds = companyIds;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
