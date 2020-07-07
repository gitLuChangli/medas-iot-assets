package com.foxconn.iot.assets.service;

import java.util.List;

import com.foxconn.iot.assets.model.UmsResourceCategory;

/**
 * 后台资源分类管理Service
 */
public interface UmsResourceCategoryService {

    /**
     * 获取所有资源分类
     */
    List<UmsResourceCategory> listAll();

    /**
     * 创建资源分类
     */
    int create(UmsResourceCategory umsResourceCategory);

    /**
     * 修改资源分类
     */
    int update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * 删除资源分类
     */
    int delete(Long id);
}
