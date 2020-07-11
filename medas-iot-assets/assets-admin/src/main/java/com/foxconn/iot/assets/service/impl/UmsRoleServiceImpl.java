package com.foxconn.iot.assets.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.foxconn.iot.assets.dao.UmsRoleDao;
import com.foxconn.iot.assets.dao.UmsRolePermissionRelationDao;
import com.foxconn.iot.assets.mapper.UmsRoleMapper;
import com.foxconn.iot.assets.mapper.UmsRoleMenuRelationMapper;
import com.foxconn.iot.assets.mapper.UmsRolePermissionRelationMapper;
import com.foxconn.iot.assets.mapper.UmsRoleResourceRelationMapper;
import com.foxconn.iot.assets.model.UmsMenu;
import com.foxconn.iot.assets.model.UmsPermission;
import com.foxconn.iot.assets.model.UmsResource;
import com.foxconn.iot.assets.model.UmsRole;
import com.foxconn.iot.assets.model.UmsRoleExample;
import com.foxconn.iot.assets.model.UmsRoleMenuRelation;
import com.foxconn.iot.assets.model.UmsRoleMenuRelationExample;
import com.foxconn.iot.assets.model.UmsRolePermissionRelation;
import com.foxconn.iot.assets.model.UmsRolePermissionRelationExample;
import com.foxconn.iot.assets.model.UmsRoleResourceRelation;
import com.foxconn.iot.assets.model.UmsRoleResourceRelationExample;
import com.foxconn.iot.assets.service.UmsAdminCacheService;
import com.foxconn.iot.assets.service.UmsRoleService;
import com.github.pagehelper.PageHelper;

/**
 * 后台角色管理Service实现类
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
	@Autowired
	private UmsRoleMapper roleMapper;
	@Autowired
	private UmsRolePermissionRelationMapper rolePermissionRelationMapper;
	@Autowired
	private UmsRoleMenuRelationMapper roleMenuRelationMapper;
	@Autowired
	private UmsRoleResourceRelationMapper roleResourceRelationMapper;
	@Autowired
	private UmsRolePermissionRelationDao rolePermissionRelationDao;
	@Autowired
	private UmsRoleDao roleDao;
	@Autowired
	private UmsAdminCacheService adminCacheService;

	@Override
	public int create(UmsRole role) {
		role.setCreateTime(new Date());
		role.setAdminCount(0);
		role.setSort(0);
		return roleMapper.insert(role);
	}

	@Override
	public int update(Long id, UmsRole role) {
		role.setId(id);
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	@Transactional
	public int delete(List<Long> ids) {
		UmsRoleExample example = new UmsRoleExample();
		example.createCriteria().andIdIn(ids);
		
		/** 删除资源关系 */
		UmsRoleResourceRelationExample rre = new UmsRoleResourceRelationExample();
		rre.createCriteria().andRoleIdIn(ids);
		roleResourceRelationMapper.deleteByExample(rre);
		
		/** 删除菜单资源 */
		UmsRoleMenuRelationExample rmr = new UmsRoleMenuRelationExample();
		rmr.createCriteria().andRoleIdIn(ids);
		roleMenuRelationMapper.deleteByExample(rmr);
		
		int count = roleMapper.deleteByExample(example);
		adminCacheService.delResourceListByRoleIds(ids);
		return count;
	}

	@Override
	public List<UmsPermission> getPermissionList(Long roleId) {
		return rolePermissionRelationDao.getPermissionList(roleId);
	}

	@Override
	public int updatePermission(Long roleId, List<Long> permissionIds) {
		// 先删除原有关系
		UmsRolePermissionRelationExample example = new UmsRolePermissionRelationExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		rolePermissionRelationMapper.deleteByExample(example);
		// 批量插入新关系
		List<UmsRolePermissionRelation> relationList = new ArrayList<>();
		for (Long permissionId : permissionIds) {
			UmsRolePermissionRelation relation = new UmsRolePermissionRelation();
			relation.setRoleId(roleId);
			relation.setPermissionId(permissionId);
			relationList.add(relation);
		}
		return rolePermissionRelationDao.insertList(relationList);
	}

	@Override
	public List<UmsRole> list() {
		return roleMapper.selectByExample(new UmsRoleExample());
	}

	@Override
	public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		UmsRoleExample example = new UmsRoleExample();
		if (!StringUtils.isEmpty(keyword)) {
			example.createCriteria().andNameLike("%" + keyword + "%");
		}
		return roleMapper.selectByExample(example);
	}

	@Override
	public List<UmsMenu> getMenuList(Long adminId) {
		return roleDao.getMenuList(adminId);
	}

	@Override
	public List<UmsMenu> listMenu(Long roleId) {
		return roleDao.getMenuListByRoleId(roleId);
	}

	@Override
	public List<UmsResource> listResource(Long roleId) {
		return roleDao.getResourceListByRoleId(roleId);
	}

	@Override
	public int allocMenu(Long roleId, List<Long> menuIds) {
		// 先删除原有关系
		UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		roleMenuRelationMapper.deleteByExample(example);
		// 批量插入新关系
		for (Long menuId : menuIds) {
			UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
			relation.setRoleId(roleId);
			relation.setMenuId(menuId);
			roleMenuRelationMapper.insert(relation);
		}
		return menuIds.size();
	}

	@Override
	public int allocResource(Long roleId, List<Long> resourceIds) {
		// 先删除原有关系
		UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		roleResourceRelationMapper.deleteByExample(example);
		// 批量插入新关系
		for (Long resourceId : resourceIds) {
			UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
			relation.setRoleId(roleId);
			relation.setResourceId(resourceId);
			roleResourceRelationMapper.insert(relation);
		}
		adminCacheService.delResourceListByRole(roleId);
		return resourceIds.size();
	}
}
