package com.foxconn.iot.assets.mongo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.common.api.Snowflaker;
import com.foxconn.iot.assets.mongo.dao.AssetsDao;
import com.foxconn.iot.assets.mongo.dao.WorkOrderDao;
import com.foxconn.iot.assets.mongo.document.Asset;
import com.foxconn.iot.assets.mongo.document.AssetSimple;
import com.foxconn.iot.assets.mongo.document.WorkOrder;
import com.foxconn.iot.assets.mongo.document.WorkOrderItem;
import com.foxconn.iot.assets.mongo.service.WorkOrderService;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
	
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private AssetsDao assetsDao;

	@Override
	public void create(Long companyId, List<Long> assetIds) {
		List<Asset> assets = assetsDao.query(companyId, assetIds);
		WorkOrder workOrder = new WorkOrder();
		List<WorkOrderItem> items = new ArrayList<>();
		for (Asset asset : assets) {
			AssetSimple simple = new AssetSimple();
			WorkOrderItem item = new WorkOrderItem();
			BeanUtils.copyProperties(asset, simple);
			item.setAsset(simple);
			items.add(item);
		}
		workOrder.setId(Snowflaker.getId());
		workOrder.setCompanyId(companyId);
		workOrder.setCount(items.size());
		workOrder.setCounted(0);
		workOrder.setStatus(0);
		workOrder.setItems(items);
		workOrder.setCreateTime(new Date());
		workOrderDao.create(workOrder);
	}

	@Override
	public Page<WorkOrder> query(Long companyId, String start, String end, String keyword, Pageable pageable) {
		return workOrderDao.query(companyId, start, end, keyword, pageable);
	}

	@Override
	public long setWorkers(Long woId, List<String> usernames) {
		return workOrderDao.setWorkers(woId, usernames);
	}

	@Override
	public long complete(Long woId, String note) {
		return workOrderDao.complete(woId, note);
	}

	@Override
	public long delete(Long id) {
		return workOrderDao.delete(id);
	}

	@Override
	public Page<WorkOrder> queryByUsername(String username, String start, String end, Pageable pageable) {
		return workOrderDao.queryByUsername(username, start, end, pageable);
	}
}
