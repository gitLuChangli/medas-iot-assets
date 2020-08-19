package com.foxconn.iot.assets.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

public class ModifyMyself {
	
	public interface Information {}
	
	public interface Password {}
	
	@JsonView(Information.class)
	private String email;
	
	@JsonView(Information.class)
	private String phone;
	
	@JsonView(Information.class)
	private String ext;

	@JsonView(Password.class)
	@NotBlank(message = "旧密码不能为空")
	private String password;
	
	@JsonView(Password.class)
	@NotBlank(message = "新密码不能为空")
	private String newpwd;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
}
