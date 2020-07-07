package com.foxconn.iot.assets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxconn.iot.assets.model.UmsMenu;
import com.foxconn.iot.assets.model.UmsResource;

/**
 * 自定义后台角色管理Dao
 */
public interface UmsRoleDao {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
    /**
     * 根据角色ID获取菜单
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色ID获取资源
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
