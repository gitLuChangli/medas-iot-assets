package com.foxconn.iot.assets.service;

import java.util.List;

import com.foxconn.iot.assets.dto.UmsPermissionNode;
import com.foxconn.iot.assets.model.UmsPermission;

/**
 * 后台用户权限管理Service
 */
public interface UmsPermissionService {
    /**
     * 添加权限
     */
    int create(UmsPermission permission);

    /**
     * 修改权限
     */
    int update(Long id,UmsPermission permission);

    /**
     * 批量删除权限
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     */
    List<UmsPermissionNode> treeList();

    /**
     * 获取所有权限
     */
    List<UmsPermission> list();
}
