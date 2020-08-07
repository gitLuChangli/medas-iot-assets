package com.foxconn.iot.assets.mongo.document;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Document(collection = "asset_epc_prefix")
@CompoundIndexes(@CompoundIndex(name = "asset_epc_prefix_uq", def = "{'prefix': 1, 'value': 1}"))
public class AssetEpcPrefix {

	@JsonFormat(shape = Shape.STRING)
	private Long id;
	
	@JsonFormat(shape = Shape.STRING)
	private Long companyId;
	
	private String prefix;
	
	private String value;

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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
