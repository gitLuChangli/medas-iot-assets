package com.foxconn.iot.assets.service;

import java.util.List;

import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.model.UmsResource;

/**
 * 后台用户缓存操作类
 */
public interface UmsAdminCacheService {
    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Long resourceId);

    /**
     * 获取缓存后台用户信息
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 获取缓存后台用户资源列表
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置后台后台用户资源列表
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);
    
    /**
     * 设置验证码
     * */
    void setVerifyCode(String username, String verify);
    
    /**
     * 获取验证码
     */
    String getVerifyCode(String username);
    
    /**
     * 删除验证码
     */
    void deleteVerifyCode(String username);
    
    /**
     * 锁定用户名
     */
    void lockUsername(String username, int times);
    
    /**
     * 查看用户锁定次数 
     */
    int getUsernameLockedTimes(String username);
    
    /**
     * 解锁用户名 
     */
    void unlockUsername(String username);
}
