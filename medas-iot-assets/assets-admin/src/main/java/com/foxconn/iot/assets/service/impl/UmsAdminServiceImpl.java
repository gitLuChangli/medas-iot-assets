package com.foxconn.iot.assets.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.foxconn.iot.assets.dao.UmsAdminDao;
import com.foxconn.iot.assets.dao.UmsAdminRoleRelationDao;
import com.foxconn.iot.assets.dao.UmsCompanyRelationDao;
import com.foxconn.iot.assets.dto.UmsAdminDto;
import com.foxconn.iot.assets.dto.UmsAdminLoginParam;
import com.foxconn.iot.assets.dto.UmsAdminParam;
import com.foxconn.iot.assets.mapper.UmsAdminLoginLogMapper;
import com.foxconn.iot.assets.mapper.UmsAdminMapper;
import com.foxconn.iot.assets.mapper.UmsAdminRoleRelationMapper;
import com.foxconn.iot.assets.mapper.UmsCompanyMapper;
import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.model.UmsAdminExample;
import com.foxconn.iot.assets.model.UmsAdminLoginLog;
import com.foxconn.iot.assets.model.UmsAdminLoginLogExample;
import com.foxconn.iot.assets.model.UmsAdminRoleRelation;
import com.foxconn.iot.assets.model.UmsAdminRoleRelationExample;
import com.foxconn.iot.assets.model.UmsAdminVo;
import com.foxconn.iot.assets.model.UmsCompany;
import com.foxconn.iot.assets.model.UmsResource;
import com.foxconn.iot.assets.model.UmsRole;
import com.foxconn.iot.assets.security.util.JwtTokenUtil;
import com.foxconn.iot.assets.service.UmsAdminCacheService;
import com.foxconn.iot.assets.service.UmsAdminService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

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
	private UmsAdminLoginLogMapper loginLogMapper;
	@Autowired
	private UmsAdminCacheService adminCacheService;
	@Autowired
	private UmsAdminCompanyDao adminCompanyDao;
	@Autowired
	private UmsCompanyRelationDao companyRelationDao;
	@Autowired
	private UmsCompanyMapper companyMapper;
	@Autowired
	private UmsAdminDao adminDao;

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
		umsAdmin.setId(Snowflaker.getId());
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
		String agent = request.getHeader("Agent");
		if (!StringUtils.isEmpty(agent)) {
			loginLog.setUserAgent(agent);
		} else {
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));		
			Browser browser = userAgent.getBrowser();
			OperatingSystem operatingSystem = userAgent.getOperatingSystem();
			Version browserVersion = userAgent.getBrowserVersion();		
			if (browser != null) {
				agent = browser.getName();
			}
			if (browserVersion != null) {
				agent += browserVersion.getVersion();
			}
			if (operatingSystem != null) {
				agent += "/" + operatingSystem.getName(); 
			}
		}
		loginLog.setUserAgent(agent);
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
				roleRelation.setId(Snowflaker.getId());
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
	public int updatePassword(String username, String oldPassword, String newPassword) {
		AdminUserDetails user = loadUserByUsername(username);
		if (passwordEncoder.matches(user.getPassword(), oldPassword)) {
			return -1;
		}
		UmsAdmin admin = new UmsAdmin();
		admin.setId(user.getUserId());
		admin.setPassword(passwordEncoder.encode(newPassword));
		int result = adminMapper.updateByPrimaryKeySelective(admin);
		if (result > 0) adminCacheService.delAdmin(user.getUserId());
		return result;
	}

	@Override
	public AdminUserDetails loadUserByUsername(String username) {
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
		adminCacheService.delAdmin(id);
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
		adminCacheService.delAdmin(id);
		return adminMapper.updateByPrimaryKeySelective(umsAdmin);
	}
	
	@Override
	public int resetPassword(Long id) {
		UmsAdmin umsAdmin = new UmsAdmin();
		umsAdmin.setId(id);
		umsAdmin.setPassword(passwordEncoder.encode("password"));
		adminCacheService.delAdmin(id);
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

	@Override
	public UmsAdminVo queryInfo(String username) {
		return adminDao.queryInfo(username);
	}

	@Override
	public int updateInformation(Long userid, String email, String phone, String ext) {
		UmsAdmin admin = new UmsAdmin();
		admin.setId(userid);
		admin.setEmail(email);
		admin.setPhone(phone);
		admin.setExt(ext);
		return adminMapper.updateByPrimaryKeySelective(admin);
	}
	
	@Override
	public List<UmsAdminLoginLog> listLoginLog(Long userid, Integer pageSize, Integer pageNum) {
		UmsAdminLoginLogExample example = new UmsAdminLoginLogExample();
		UmsAdminLoginLogExample.Criteria criteria = example.createCriteria();
		criteria.andAdminIdEqualTo(userid);
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return loginLogMapper.selectByExample(example);
	}
}
