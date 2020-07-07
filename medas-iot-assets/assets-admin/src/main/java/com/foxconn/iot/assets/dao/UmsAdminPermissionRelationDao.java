package com.foxconn.iot.assets.dao;

import org.apache.ibatis.annotations.Param;

import com.foxconn.iot.assets.model.UmsAdminPermissionRelation;

import java.util.List;

/**
 * 自定义用户权限关系管理Dao
 */
public interface UmsAdminPermissionRelationDao {
	
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<UmsAdminPermissionRelation> list);
}
