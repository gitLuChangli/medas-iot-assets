package com.foxconn.iot.assets.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

public class UmsCompanyDto {
	
	public interface Create {}
	
	@JsonFormat(shape = Shape.STRING)	
	private Long id;
	
	@JsonView(Create.class)
	@NotBlank(message = "費用代碼不能為空")
	private String feeCode;
	
	@JsonView(Create.class)
	@NotBlank(message = "部門名稱不能為空")
	private String name;
	
	@JsonView(Create.class)
	private String area;
	
	private String note;
	
	private Integer status;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "GTM+8")
	private Date createTime;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<Long> ancestorIds;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<UmsCompanyDto> descendants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Long> getAncestorIds() {
		return ancestorIds;
	}

	public void setAncestorIds(List<Long> ancestorIds) {
		this.ancestorIds = ancestorIds;
	}

	public List<UmsCompanyDto> getDescendants() {
		return descendants;
	}

	public void setDescendants(List<UmsCompanyDto> descendants) {
		this.descendants = descendants;
	}
}
