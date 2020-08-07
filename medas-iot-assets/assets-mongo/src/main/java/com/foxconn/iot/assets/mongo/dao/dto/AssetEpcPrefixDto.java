package com.foxconn.iot.assets.mongo.dao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AssetEpcPrefixDto {
	
	@NotNull(message = "部门编号不能为空")
	private Long companyId;
	
	@NotEmpty(message = "前缀不能为空")
	private String prefix;
	
	@NotEmpty(message = "前缀代替的值不能为空")
	private String value;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
