package com.foxconn.iot.assets.mongo.document;

import io.swagger.annotations.ApiModelProperty;

public class WorkOrderItem {
	
	@ApiModelProperty(value = "資產信息")
	private AssetSimple asset;
	
	@ApiModelProperty(value = "盤點狀態")
	private int status;
	
	@ApiModelProperty(value = "盤點人工號")
	private String username;
	
	@ApiModelProperty(value = "盤點人姓名")
	private String nickname;
	
	@ApiModelProperty(value = "盤點時間")
	private String completeTime;
	
	@ApiModelProperty(value = "備註")
	private String note;

	@ApiModelProperty(value = "樓棟")
	private String building;
	
	@ApiModelProperty(value = "樓層")
	private String floor;
	
	@ApiModelProperty(value = "線體")
	private String xianti;
	
	public AssetSimple getAsset() {
		return asset;
	}

	public void setAsset(AssetSimple asset) {
		this.asset = asset;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
}
