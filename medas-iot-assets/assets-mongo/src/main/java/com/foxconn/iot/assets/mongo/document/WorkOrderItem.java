package com.foxconn.iot.assets.mongo.document;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModelProperty;

public class WorkOrderItem {
	
	@ApiModelProperty(value = "資產編號")
	@ExcelProperty(value = "編號")
	@JsonFormat(shape = Shape.STRING)
	private Long id;
	
	@ApiModelProperty(value = "管制編號")
	@ExcelProperty(value = "管制編號")
	private String gzhNum;
	
	@ApiModelProperty(value = "財產編號")
	@ExcelProperty(value = "財產編號")
	private String cchNum;
	
	@ApiModelProperty(value = "設備名稱")
	@ExcelProperty(value = "設備名稱")
	private String shbName;
	
	@ApiModelProperty(value = "設備品牌")
	@ExcelProperty(value = "設備品牌")
	private String shbBrand;
	
	@ApiModelProperty(value = "設備規格")
	@ExcelProperty(value = "設備規格")
	private String shbSpec;
	
	@ApiModelProperty(value = "樓棟")
	@ExcelProperty(value = "樓棟")
	private String building;
	
	@ApiModelProperty(value = "樓層")
	@ExcelProperty(value = "樓層")
	private String floor;
	
	@ApiModelProperty(value = "線體")
	@ExcelProperty(value = "線體")
	private String xianti;
	
	@ApiModelProperty(value = "盤點人工號")
	@ExcelProperty(value = "盤點人工號")
	private String username;
	
	@ApiModelProperty(value = "盤點人姓名")
	@ExcelProperty(value = "盤點人姓名")
	private String nickname;
	
	@ApiModelProperty(value = "盤點時間")
	@ExcelProperty(value = "盤點時間")
	private String completeTime;
	
	@ApiModelProperty(value = "備註")
	@ExcelProperty(value = "備註")
	private String note;

	@ApiModelProperty(value = "盤點狀態")
	@ExcelProperty(value = "盤點狀態")
	private int status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGzhNum() {
		return gzhNum;
	}

	public void setGzhNum(String gzhNum) {
		this.gzhNum = gzhNum;
	}

	public String getCchNum() {
		return cchNum;
	}

	public void setCchNum(String cchNum) {
		this.cchNum = cchNum;
	}

	public String getShbName() {
		return shbName;
	}

	public void setShbName(String shbName) {
		this.shbName = shbName;
	}

	public String getShbBrand() {
		return shbBrand;
	}

	public void setShbBrand(String shbBrand) {
		this.shbBrand = shbBrand;
	}

	public String getShbSpec() {
		return shbSpec;
	}

	public void setShbSpec(String shbSpec) {
		this.shbSpec = shbSpec;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getXianti() {
		return xianti;
	}

	public void setXianti(String xianti) {
		this.xianti = xianti;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
