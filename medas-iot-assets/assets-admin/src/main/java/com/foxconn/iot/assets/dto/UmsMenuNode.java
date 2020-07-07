package com.foxconn.iot.assets.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.foxconn.iot.assets.model.UmsMenu;

/**
 * 后台菜单节点封装
 */
public class UmsMenuNode extends UmsMenu {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "子级菜单")
    private List<UmsMenuNode> children;

	public List<UmsMenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<UmsMenuNode> children) {
		this.children = children;
	}
}
