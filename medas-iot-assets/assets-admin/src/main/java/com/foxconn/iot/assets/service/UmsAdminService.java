package com.foxconn.iot.assets.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.foxconn.iot.assets.bo.AdminUserDetails;
import com.foxconn.iot.assets.dto.UmsAdminDto;
import com.foxconn.iot.assets.dto.UmsAdminLoginParam;
import com.foxconn.iot.assets.dto.UmsAdminParam;
import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.model.UmsAdminLoginLog;
import com.foxconn.iot.assets.model.UmsAdminVo;
import com.foxconn.iot.assets.model.UmsPermission;
import com.foxconn.iot.assets.model.UmsResource;
import com.foxconn.iot.assets.model.UmsRole;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    UmsAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UmsAdmin> list(String keyword, Long companyId, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改用户的+-权限
     */
    @Transactional
    int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 用户修改密码
     */
    @Transactional
    int updatePassword(String username, String oldPassword, String newPassword);
    
    /**
     * 用户修改个人信息
     * 
     * @param userid
     * @param email
     * @param phone
     * @param ext
     * @return
     */
    @Transactional
    int updateInformation(Long userid, String email, String phone, String ext);

    /**
     * 获取用户信息
     */
    AdminUserDetails loadUserByUsername(String username);
    
    int create(UmsAdminDto admin);
    
    @Transactional
    int update(Long id, UmsAdminDto admin);
    
    List<Long> queryCompanyRelation(Long userid);
    
    @Transactional
    int disable(Long id, int status);
    
    @Transactional
    int resetPassword(Long id);
    
    List<UmsAdmin> queryByCompany(Long companyId);
    
    /**
     * 檢驗驗證碼
     */
    boolean checkVerifyCode(UmsAdminLoginParam admin);
    
    /**
     * 获取驗證碼 
     */
    String getVerifyCode(String username);
    
    /**
     * 設置驗證碼
     */
    void setVerifyCode(String username, String verifyCode);
    
    /**
     * 查看用户基本信息 
     */
    UmsAdminVo queryInfo(String username);
    
    /**
     * 列出用户的登录记录 
     */
    List<UmsAdminLoginLog> listLoginLog(Long userid, Integer pageSize, Integer pageNum);
}
