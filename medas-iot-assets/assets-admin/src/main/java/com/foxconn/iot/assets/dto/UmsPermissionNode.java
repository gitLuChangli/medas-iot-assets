package com.foxconn.iot.assets.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.foxconn.iot.assets.model.UmsPermission;

/**
 * 后台权限节点封装
 */
public class UmsPermissionNode extends UmsPermission {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "子级权限")
    private List<UmsPermissionNode> children;

	public List<UmsPermissionNode> getChildren() {
		return children;
	}

	public void setChildren(List<UmsPermissionNode> children) {
		this.children = children;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
