package com.foxconn.iot.assets.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Document(collection = "assets")
@CompoundIndexes({ @CompoundIndex(name = "company_GZ", def = "{'companyId': 1, 'gzhNum': 1}", unique = true) })
public class Asset {

	@Id
	@JsonFormat(shape = Shape.STRING)
	private Long id;

	@JsonFormat(shape = Shape.STRING)
	private Long companyId;

	private String gzhNum;

	private String cchNum;

	private String shbName;

	private String shbBrand;

	private String shbSpec;

	private String building;

	private String floor;

	private String xianti;

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
