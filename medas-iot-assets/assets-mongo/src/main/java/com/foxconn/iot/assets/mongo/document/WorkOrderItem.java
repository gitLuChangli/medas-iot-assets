package com.foxconn.iot.assets.mongo.document;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date completeTime;
	
	@ApiModelProperty(value = "備註")
	private String note;

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
