package com.foxconn.iot.assets.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.dao.UmsAdminRoleRelationDao;
import com.foxconn.iot.assets.mapper.UmsAdminRoleRelationMapper;
import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.model.UmsAdminRoleRelation;
import com.foxconn.iot.assets.model.UmsAdminRoleRelationExample;
import com.foxconn.iot.assets.model.UmsResource;
import com.foxconn.iot.assets.security.service.RedisService;
import com.foxconn.iot.assets.service.UmsAdminCacheService;
import com.foxconn.iot.assets.service.UmsAdminService;

import cn.hutool.core.collection.CollUtil;

/**
 * UmsAdminCacheService实现类
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resources}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminService.getItem(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = adminRoleRelationDao.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }

	@Override
	public void setVerifyCode(String username, String verify) {
		String key = REDIS_DATABASE + ":VC:" + username;
		redisService.set(key, verify, 3000);
	}

	@Override
	public String getVerifyCode(String username) {
		String key = REDIS_DATABASE + ":VC:" + username;
		return (String) redisService.get(key);
	}
	
	@Override
	public void deleteVerifyCode(String username) {
		String key = REDIS_DATABASE + ":VC:" + username;
		redisService.del(key);
	}
	
	@Override
	public void lockUsername(String username, int times) {
		String key = REDIS_DATABASE + ":LOCK:" + username;
		redisService.set(key, times, 60 * 60 * 2);
	}
	
	@Override
	public int getUsernameLockedTimes(String username) {
		String key = REDIS_DATABASE + ":LOCK:" + username;
		Object object = redisService.get(key);
		if (object == null) return 0;
		return (Integer) object;
	}
	
	@Override
	public void unlockUsername(String username) {
		String key = REDIS_DATABASE + ":LOCK:" + username;
		redisService.del(key);
	}
}
