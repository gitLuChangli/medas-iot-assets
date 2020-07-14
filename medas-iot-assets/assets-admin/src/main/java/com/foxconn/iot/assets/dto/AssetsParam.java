package com.foxconn.iot.assets.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.foxconn.iot.assets.mongo.document.Asset;

public class AssetsParam {
	
	@NotNull(message = "部門濱蒿不能為空")
	private Long companyId;
	
	private List<Asset> assets;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
}
