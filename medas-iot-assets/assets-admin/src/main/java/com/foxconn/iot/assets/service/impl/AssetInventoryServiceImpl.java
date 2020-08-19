package com.foxconn.iot.assets.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foxconn.iot.assets.dao.UmsAdminDao;
import com.foxconn.iot.assets.mongo.dao.dto.AssetHistoryItem;
import com.foxconn.iot.assets.mongo.document.Asset;
import com.foxconn.iot.assets.mongo.document.WorkOrder;
import com.foxconn.iot.assets.mongo.service.AssetsService;
import com.foxconn.iot.assets.mongo.service.WorkOrderService;
import com.foxconn.iot.assets.service.AssetInventoryService;

@Service
public class AssetInventoryServiceImpl implements AssetInventoryService {

	@Autowired
	private AssetsService assetsService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private UmsAdminDao adminDao;

	@Override
	public long saveAssets(Long companyId, List<Asset> assets) {
		return assetsService.save(companyId, assets);
	}

	@Override
	public Page<Asset> queryAssets(Long companyId, Pageable pageable) {
		return assetsService.query(companyId, pageable);
	}

	@Override
	public void createWorkOrder(Long companyId, List<Long> assetIds) {
		List<Asset> assets = assetsService.query(companyId, assetIds);
		List<Long> ids = assets.stream().map(Asset::getId).collect(Collectors.toList());
		workOrderService.create(companyId, ids);
	}

	@Override
	public Page<WorkOrder> queryWorkOrders(Long companyId, String start, String end, String keyword,
			Pageable pageable) {
		return workOrderService.query(companyId, start, end, keyword, pageable);
	}

	@Override
	public long setInventoryWorkers(Long companyId, Long workId, List<String> usernames) {
		List<String> names = adminDao.matchUsernames(companyId, usernames);
		if (names.size() > 0 && names.get(0) != null) {
			return workOrderService.setWorkers(workId, names);
		}
		return 0;
	}

	@Override
	public long complete(Long companyId, Long id, String note) {
		return workOrderService.complete(companyId, id, note);
	}

	@Override
	public long deleteWorkOrder(Long companyId, Long id) {
		return workOrderService.delete(companyId, id);
	}

	@Override
	public Page<WorkOrder> queryWorkOrdersByUsername(String username, String start, String end, Pageable pageable) {
		return workOrderService.queryByUsername(username, start, end, pageable);
	}

	@Override
	public long syncHistory(ArrayList<AssetHistoryItem> items) {
		return workOrderService.syncHistory(items);
	}

	@Override
	public WorkOrder queryWorkOrder(Long companyId, Long workId) {
		return workOrderService.query(companyId, workId);
	}
}
