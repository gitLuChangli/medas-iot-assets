package com.foxconn.iot.assets.mongo.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "work_order")
@CompoundIndexes({ @CompoundIndex(name = "work_order_num", def = "{'num': 1}", unique = true) })
public class WorkOrder {
	
	@Id
	@JsonFormat(shape = Shape.STRING)
	private Long id;
	
	@ApiModelProperty(value = "工單號")
	private String num;

	@ApiModelProperty(value = "部門編號")
	@JsonFormat(shape = Shape.STRING)
	private Long companyId;
	
	@ApiModelProperty(value = "資產清單")
	private List<WorkOrderItem> items;
	
	@ApiModelProperty(value = "資產數量")
	private Integer count;
	
	@ApiModelProperty(value = "已盤點數量")
	private Integer counted;
	
	@ApiModelProperty(value = "工單狀態")
	private Integer status;
	
	@ApiModelProperty(value = "盤點人")
	private List<String> usernames;
	
	@ApiModelProperty(value = "創建時間")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	@ApiModelProperty(value = "結單時間")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date completeTime;
	
	@ApiModelProperty(value = "備註")
	private String note;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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
	
	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
