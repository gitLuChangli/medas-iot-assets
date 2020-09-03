package com.foxconn.iot.assets.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TagPrefixDto {
		
	@NotNull(message="编号不能为空")
	private Long id;
	
	@NotBlank(message = "字段不能为空")	
	private String k;
	
	@NotBlank(message = "值不能为空")	
	private String v;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}
}
