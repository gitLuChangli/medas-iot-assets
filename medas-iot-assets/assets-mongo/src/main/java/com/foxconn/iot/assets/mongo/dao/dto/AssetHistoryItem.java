package com.foxconn.iot.assets.mongo.dao.dto;

import io.swagger.annotations.ApiModelProperty;

public class AssetHistoryItem {
	
	@ApiModelProperty(value = "工單編號")
	private Long workId;
	
	@ApiModelProperty(value = "資產編號")
	private Long assetId;
	
	@ApiModelProperty(value = "盤點人工號")
	private String username;
	
	@ApiModelProperty(value = "盤點人姓名")
	private String nickname;
	
	@ApiModelProperty(value = "盤點時間")
	private String completeTime;
	
	@ApiModelProperty(value = "備註")
	private String note;

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
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
}
