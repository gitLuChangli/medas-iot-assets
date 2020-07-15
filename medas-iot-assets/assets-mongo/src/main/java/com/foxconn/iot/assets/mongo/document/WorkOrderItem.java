package com.foxconn.iot.assets.mongo.document;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModelProperty;

public class WorkOrderItem {
	
	@ApiModelProperty(value = "资产信息")
	private AssetSimple asset;
	
	@ApiModelProperty(value = "盘点状态")
	private int statue;
	
	@ApiModelProperty(value = "盘点人工号")
	private String username;
	
	@ApiModelProperty(value = "盘点人姓名")
	private String nickname;
	
	@ApiModelProperty(value = "完成时间")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "GTM+8")
	private Date completeTime;
	
	@ApiModelProperty(value = "备注")
	private String note;

	public AssetSimple getAsset() {
		return asset;
	}

	public void setAsset(AssetSimple asset) {
		this.asset = asset;
	}

	public int getStatue() {
		return statue;
	}

	public void setStatue(int statue) {
		this.statue = statue;
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
