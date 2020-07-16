package com.foxconn.iot.assets.mongo.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModelProperty;

public class AssetSimple {
	
	@JsonFormat(shape = Shape.STRING)
	private Long id;
	
	@ApiModelProperty(value = "管制編號")
	private String gzhNum;
	
	@ApiModelProperty(value = "財產編號")
	private String cchNum;
	
	@ApiModelProperty(value = "設備名稱")
	private String shbName;
	
	@ApiModelProperty(value = "設備品牌")
	private String shbBrand;
	
	@ApiModelProperty(value = "設備規格")
	private String shbSpec;
	
	@ApiModelProperty(value = "樓棟")
	private String building;

	@ApiModelProperty(value = "樓層")
	private String floor;

	@ApiModelProperty(value = "線體")
	private String xianti;

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
}
