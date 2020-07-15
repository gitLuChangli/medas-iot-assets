package com.foxconn.iot.assets.mongo.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "work_order")
public class WorkOrder {
	
	@ApiModelProperty(value = "工单号")
	@Id
	@JsonFormat(shape = Shape.STRING)
	private Long id;

	@ApiModelProperty(value = "部门编号")
	@JsonFormat(shape = Shape.STRING)
	private Long companyId;
	
	@ApiModelProperty(value = "盘点资产清单")
	private List<WorkOrderItem> items;
	
	@ApiModelProperty(value = "盘点资产数量")
	private Integer count;
	
	@ApiModelProperty(value = "已盘点数量")
	private Integer counted;
	
	@ApiModelProperty(value = "工单状态")
	private Integer status;
	
	@ApiModelProperty(value = "盤點人")
	private List<String> usernames;
	
	@ApiModelProperty(value = "創建時間")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "GTM+8")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public List<WorkOrderItem> getItems() {
		return items;
	}

	public void setItems(List<WorkOrderItem> items) {
		this.items = items;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCounted() {
		return counted;
	}

	public void setCounted(Integer counted) {
		this.counted = counted;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<String> getUsernames() {
		return usernames;
	}

	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
