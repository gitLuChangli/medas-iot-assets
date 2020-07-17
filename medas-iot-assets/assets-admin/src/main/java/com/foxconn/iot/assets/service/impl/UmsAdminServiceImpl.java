package com.foxconn.iot.assets.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.foxconn.iot.assets.bo.AdminUserDetails;
import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.common.api.VerificationCode;
import com.foxconn.iot.assets.dao.UmsAdminCompanyDao;
import com.foxconn.iot.assets.dao.UmsAdminPermissionRelationDao;
import com.foxconn.iot.assets.dao.UmsAdminRoleRelationDao;
import com.foxconn.iot.assets.dao.UmsCompanyRelationDao;
import com.foxconn.iot.assets.dto.UmsAdminDto;
import com.foxconn.iot.assets.dto.UmsAdminLoginParam;
import com.foxconn.iot.assets.dto.UmsAdminParam;
import com.foxconn.iot.assets.dto.UpdateAdminPasswordParam;
import com.foxconn.iot.assets.mapper.UmsAdminLoginLogMapper;
import com.foxconn.iot.assets.mapper.UmsAdminMapper;
import com.foxconn.iot.assets.mapper.UmsAdminPermissionRelationMapper;
import com.foxconn.iot.assets.mapper.UmsAdminRoleRelationMapper;
import com.foxconn.iot.assets.mapper.UmsCompanyMapper;
import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.model.UmsAdminExample;
import com.foxconn.iot.assets.model.UmsAdminLoginLog;
import com.foxconn.iot.assets.model.UmsAdminPermissionRelation;
import com.foxconn.iot.assets.model.UmsAdminPermissionRelationExample;
import com.foxconn.iot.assets.model.UmsAdminRoleRelation;
import com.foxconn.iot.assets.model.UmsAdminRoleRelationExample;
import com.foxconn.iot.assets.model.UmsCompany;
import com.foxconn.iot.assets.model.UmsPermission;
import com.foxconn.iot.assets.model.UmsResource;
import com.foxconn.iot.assets.model.UmsRole;
import com.foxconn.iot.assets.security.util.JwtTokenUtil;
import com.foxconn.iot.assets.service.UmsAdminCacheService;
import com.foxconn.iot.assets.service.UmsAdminService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

/**
 * UmsAdminService实现类
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UmsAdminMapper adminMapper;
	@Autowired
	private UmsAdminRoleRelationMapper adminRoleRelationMapper;
	@Autowired
	private UmsAdminRoleRelationDao adminRoleRelationDao;
	@Autowired
	private UmsAdminPermissionRelationMapper adminPermissionRelationMapper;
	@Autowired
	private UmsAdminPermissionRelationDao adminPermissionRelationDao;
	@Autowired
	private UmsAdminLoginLogMapper loginLogMapper;
	@Autowired
	private UmsAdminCacheService adminCacheService;
	@Autowired
	private UmsAdminCompanyDao adminCompanyDao;
	@Autowired
	private UmsCompanyRelationDao companyRelationDao;
	@Autowired
	private UmsCompanyMapper companyMapper;

	@Override
	public UmsAdmin getAdminByUsername(String username) {
		UmsAdmin admin = adminCacheService.getAdmin(username);
		if (admin != null)
			return admin;
		UmsAdminExample example = new UmsAdminExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<UmsAdmin> adminList = adminMapper.selectByExample(example);
		if (adminList != null && adminList.size() > 0) {
			admin = adminList.get(0);
			adminCacheService.setAdmin(admin);
			return admin;
		}
		return null;
	}

	@Override
	public UmsAdmin register(UmsAdminParam umsAdminParam) {
		UmsAdmin umsAdmin = new UmsAdmin();
		BeanUtils.copyProperties(umsAdminParam, umsAdmin);
		umsAdmin.setCreateTime(new Date());
		umsAdmin.setStatus(1);
		// 查询是否有相同用户名的用户
		UmsAdminExample example = new UmsAdminExample();
		example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
		List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
		if (umsAdminList.size() > 0) {
			return null;
		}
		// 将密码进行加密操作
		String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
		umsAdmin.setPassword(encodePassword);
		adminMapper.insert(umsAdmin);
		return umsAdmin;
	}

	@Override
	public String login(String username, String password) {
		String token = null;
		// 密码需要客户端加密后传递
		try {
			UserDetails userDetails = loadUserByUsername(username);
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				int times = adminCacheService.getUsernameLockedTimes(username);
				adminCacheService.lockUsername(username, times + 1);
				if (times > 5) {
					adminCacheService.setVerifyCode(username, VerificationCode.getCode(4));
				}
				throw new BadCredentialsException("密碼不正確");
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			token = jwtTokenUtil.generateToken(userDetails);
			updateLoginTimeByUsername(username);
			insertLoginLog(username);
			adminCacheService.unlockUsername(username);
		} catch (AuthenticationException e) {
			LOGGER.warn("登录异常:{}", e.getMessage());
		}
		return token;
	}

	/**
	 * 添加登录记录
	 * 
	 * @param username 用户名
	 */
	private void insertLoginLog(String username) {
		UmsAdmin admin = getAdminByUsername(username);
		if (admin == null)
			return;
		UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
		loginLog.setAdminId(admin.getId());
		loginLog.setCreateTime(new Date());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		loginLog.setIp(request.getRemoteAddr());
		loginLogMapper.insert(loginLog);
	}

	/**
	 * 根据用户名修改登录时间
	 */
	private void updateLoginTimeByUsername(String username) {
		UmsAdmin record = new UmsAdmin();
		record.setLoginTime(new Date());
		UmsAdminExample example = new UmsAdminExample();
		example.createCriteria().andUsernameEqualTo(username);
		adminMapper.updateByExampleSelective(record, example);
	}

	@Override
	public String refreshToken(String oldToken) {
		return jwtTokenUtil.refreshHeadToken(oldToken);
	}

	@Override
	public UmsAdmin getItem(Long id) {
		return adminMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UmsAdmin> list(String keyword, Long companyId, Integer pageSize, Integer pageNum) {		
		UmsAdminExample example = new UmsAdminExample();
		UmsAdminExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(keyword)) {
			criteria.andUsernameLike("%" + keyword + "%");
			example.or(example.createCriteria().andNicknameLike("%" + keyword + "%"));
		}
		if (companyId != null && companyId > 0) {
			List<Long> companyIds = companyRelationDao.getDescendants(companyId);
			if (companyIds != null && companyIds.size() > 0) {
				criteria.andCompanyIdIn(companyIds);
			}
		}
		PageHelper.startPage(pageNum, pageSize);
		return adminMapper.selectByExample(example);
	}

	@Override
	public int update(Long id, UmsAdmin admin) {
		admin.setId(id);
		UmsAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
		if (rawAdmin.getPassword().equals(admin.getPassword())) {
			// 与原加密密码相同的不需要修改
			admin.setPassword(null);
		} else {
			// 与原加密密码不同的需要加密修改
			if (StrUtil.isEmpty(admin.getPassword())) {
				admin.setPassword(null);
			} else {
				admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			}
		}
		int count = adminMapper.updateByPrimaryKeySelective(admin);
		adminCacheService.delAdmin(id);
		return count;
	}

	@Override
	public int delete(Long id) {
		adminCacheService.delAdmin(id);
		int count = adminMapper.deleteByPrimaryKey(id);
		adminCacheService.delResourceList(id);
		return count;
	}

	@Override
	public int updateRole(Long adminId, List<Long> roleIds) {
		int count = roleIds == null ? 0 : roleIds.size();
		// 先删除原来的关系
		UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
		adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
		adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
		// 建立新关系
		if (!CollectionUtils.isEmpty(roleIds)) {
			List<UmsAdminRoleRelation> list = new ArrayList<>();
			for (Long roleId : roleIds) {
				UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
				roleRelation.setAdminId(adminId);
				roleRelation.setRoleId(roleId);
				list.add(roleRelation);
			}
			adminRoleRelationDao.insertList(list);
		}
		adminCacheService.delResourceList(adminId);
		return count;
	}

	@Override
	public List<UmsRole> getRoleList(Long adminId) {
		return adminRoleRelationDao.getRoleList(adminId);
	}

	@Override
	public List<UmsResource> getResourceList(Long adminId) {
		List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
		if (CollUtil.isNotEmpty(resourceList)) {
			return resourceList;
		}
		resourceList = adminRoleRelationDao.getResourceList(adminId);
		if (CollUtil.isNotEmpty(resourceList)) {
			adminCacheService.setResourceList(adminId, resourceList);
		}
		return resourceList;
	}

	@Override
	public int updatePermission(Long adminId, List<Long> permissionIds) {
		// 删除原所有权限关系
		UmsAdminPermissionRelationExample relationExample = new UmsAdminPermissionRelationExample();
		relationExample.createCriteria().andAdminIdEqualTo(adminId);
		adminPermissionRelationMapper.deleteByExample(relationExample);
		// 获取用户所有角色权限
		List<UmsPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
		List<Long> rolePermissionList = permissionList.stream().map(UmsPermission::getId).collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(permissionIds)) {
			List<UmsAdminPermissionRelation> relationList = new ArrayList<>();
			// 筛选出+权限
			List<Long> addPermissionIdList = permissionIds.stream()
					.filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
			// 筛选出-权限
			List<Long> subPermissionIdList = rolePermissionList.stream()
					.filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
			// 插入+-权限关系
			relationList.addAll(convert(adminId, 1, addPermissionIdList));
			relationList.addAll(convert(adminId, -1, subPermissionIdList));
			return adminPermissionRelationDao.insertList(relationList);
		}
		return 0;
	}

	/**
	 * 将+-权限关系转化为对象
	 */
	private List<UmsAdminPermissionRelation> convert(Long adminId, Integer type, List<Long> permissionIdList) {
		List<UmsAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
			UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
			relation.setAdminId(adminId);
			relation.setType(type);
			relation.setPermissionId(permissionId);
			return relation;
		}).collect(Collectors.toList());
		return relationList;
	}

	@Override
	public List<UmsPermission> getPermissionList(Long adminId) {
		return adminRoleRelationDao.getPermissionList(adminId);
	}

	@Override
	public int updatePassword(UpdateAdminPasswordParam param) {
		if (StrUtil.isEmpty(param.getUsername()) || StrUtil.isEmpty(param.getOldPassword())
				|| StrUtil.isEmpty(param.getNewPassword())) {
			return -1;
		}
		UmsAdminExample example = new UmsAdminExample();
		example.createCriteria().andUsernameEqualTo(param.getUsername());
		List<UmsAdmin> adminList = adminMapper.selectByExample(example);
		if (CollUtil.isEmpty(adminList)) {
			return -2;
		}
		UmsAdmin umsAdmin = adminList.get(0);
		if (!passwordEncoder.matches(param.getOldPassword(), umsAdmin.getPassword())) {
			return -3;
		}
		umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
		adminMapper.updateByPrimaryKey(umsAdmin);
		adminCacheService.delAdmin(umsAdmin.getId());
		return 1;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// 获取用户信息
		UmsAdmin admin = getAdminByUsername(username);
		if (admin != null) {
			List<UmsResource> resourceList = getResourceList(admin.getId());
			return new AdminUserDetails(admin, resourceList);
		}
		throw new UsernameNotFoundException("用户名或密码错误");
	}
	
	@Override
	public int create(UmsAdminDto admin) {
		UmsAdmin umsAdmin = new UmsAdmin();
		BeanUtils.copyProperties(admin, umsAdmin);
		if (admin.getCompanyIds() == null || admin.getCompanyIds().length == 0) {
			return 0;
		}
		Long companyId = Long.parseLong(admin.getCompanyIds()[admin.getCompanyIds().length - 1]);
		UmsCompany company = companyMapper.selectByPrimaryKey(companyId);
		if (company == null) {
			return 0;
		}
		umsAdmin.setCompanyId(companyId);
		umsAdmin.setPassword(passwordEncoder.encode("password"));
		umsAdmin.setId(Snowflaker.getId());
		return adminMapper.insert(umsAdmin);
	}
	
	@Override
	public int update(Long id, UmsAdminDto admin) {
		UmsAdmin umsAdmin = new UmsAdmin();
		BeanUtils.copyProperties(admin, umsAdmin);
		umsAdmin.setId(id);
		if (admin.getCompanyIds() == null || admin.getCompanyIds().length == 0) {
			return 0;
		}
		Long companyId = Long.parseLong(admin.getCompanyIds()[admin.getCompanyIds().length - 1]);
		UmsCompany company = companyMapper.selectByPrimaryKey(companyId);
		if (company == null) {
			return 0;
		}
		umsAdmin.setCompanyId(companyId);
		umsAdmin.setId(id);
		return adminMapper.updateByPrimaryKeySelective(umsAdmin);
	}
	
	@Override
	public List<Long> queryCompanyRelation(Long userid) {
		return adminCompanyDao.queryCompanyRelation(userid);
	}
	
	@Override
	public int disable(Long id, int status) {
		UmsAdmin umsAdmin = new UmsAdmin();
		umsAdmin.setId(id);
		umsAdmin.setStatus(status);		
		return adminMapper.updateByPrimaryKeySelective(umsAdmin);
	}
	
	@Override
	public int resetPassword(Long id) {
		UmsAdmin umsAdmin = new UmsAdmin();
		umsAdmin.setId(id);
		umsAdmin.setPassword(passwordEncoder.encode("password"));
		return adminMapper.updateByPrimaryKeySelective(umsAdmin);
	}
	
	@Override
	public List<UmsAdmin> queryByCompany(Long companyId) {
		UmsAdminExample example = new UmsAdminExample();
		example.createCriteria().andCompanyIdEqualTo(companyId);
		return adminMapper.selectByExample(example);
	}
	
	@Override
	public boolean checkVerifyCode(UmsAdminLoginParam admin) {
		int times = adminCacheService.getUsernameLockedTimes(admin.getUsername());
		if (times < 5) {
			return true;
		}
		if (StringUtils.isEmpty(admin.getVerifyCode())) {
			return false;
		}
		String verifyCode = adminCacheService.getVerifyCode(admin.getUsername());
		if (StringUtils.isEmpty(verifyCode)) {
			return false;
		}
		return verifyCode.equals(admin.getVerifyCode());
	}
	
	@Override
	public String getVerifyCode(String username) {
		return adminCacheService.getVerifyCode(username);
	}
	
	@Override
	public void setVerifyCode(String username, String verifyCode) {
		adminCacheService.setVerifyCode(username, verifyCode);
	}
}
