package com.foxconn.iot.assets.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.dao.UmsAdminDao;
import com.foxconn.iot.assets.dto.UmsMenuNode;
import com.foxconn.iot.assets.mapper.UmsMenuMapper;
import com.foxconn.iot.assets.model.UmsMenu;
import com.foxconn.iot.assets.model.UmsMenuExample;
import com.foxconn.iot.assets.service.UmsMenuService;
import com.github.pagehelper.PageHelper;

/**
 * 后台菜单管理Service实现类
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
	@Autowired
	private UmsMenuMapper menuMapper;
	@Autowired
	private UmsAdminDao adminDao;

	@Override
	public int create(UmsMenu umsMenu) {
		umsMenu.setId(Snowflaker.getId());
		umsMenu.setCreateTime(new Date());
		updateLevel(umsMenu);
		return menuMapper.insert(umsMenu);
	}

	/**
	 * 修改菜单层级
	 */
	private void updateLevel(UmsMenu umsMenu) {
		if (umsMenu.getParentId() == null || umsMenu.getParentId() == 0) {
			// 没有父菜单时为一级菜单
			umsMenu.setLevel(0);
		} else {
			// 有父菜单时选择根据父菜单level设置
			UmsMenu parentMenu = menuMapper.selectByPrimaryKey(umsMenu.getParentId());
			if (parentMenu != null) {
				umsMenu.setLevel(parentMenu.getLevel() + 1);
			} else {
				umsMenu.setLevel(0);
			}
		}
	}

	@Override
	public int update(Long id, UmsMenu umsMenu) {
		umsMenu.setId(id);
		updateLevel(umsMenu);
		return menuMapper.updateByPrimaryKeySelective(umsMenu);
	}

	@Override
	public UmsMenu getItem(Long id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Long id) {
		return menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		UmsMenuExample example = new UmsMenuExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andParentIdEqualTo(parentId);
		return menuMapper.selectByExample(example);
	}

	@Override
	public List<UmsMenuNode> treeList() {
		List<UmsMenu> menuList = menuMapper.selectByExample(new UmsMenuExample());
		List<UmsMenuNode> result = menuList.stream().filter(menu -> menu.getParentId().equals(0L))
				.map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
		return result;
	}

	@Override
	public int updateHidden(Long id, Integer hidden) {
		UmsMenu umsMenu = new UmsMenu();
		umsMenu.setId(id);
		umsMenu.setHidden(hidden);
		return menuMapper.updateByPrimaryKeySelective(umsMenu);
	}

	/**
	 * 将UmsMenu转化为UmsMenuNode并设置children属性
	 */
	private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
		UmsMenuNode node = new UmsMenuNode();
		BeanUtils.copyProperties(menu, node);
		List<UmsMenuNode> children = menuList.stream().filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
				.map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
		node.setChildren(children);
		return node;
	}

	@Override
	public List<UmsMenuNode> treeList(String username) {
		List<UmsMenu> menuList = adminDao.queryByUsername(username);
		if (menuList.size() > 0 && menuList.get(0) != null) {
			List<UmsMenuNode> result = menuList.stream().filter(menu -> menu.getParentId().equals(0L))
					.map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
			return result;
		}
		return null;
	}
}
