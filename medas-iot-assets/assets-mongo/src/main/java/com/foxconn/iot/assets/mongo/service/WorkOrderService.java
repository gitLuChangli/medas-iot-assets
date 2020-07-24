package com.foxconn.iot.assets.mongo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.foxconn.iot.assets.mongo.dao.dto.AssetHistoryItem;
import com.foxconn.iot.assets.mongo.document.WorkOrder;

public interface WorkOrderService {
	
	void create(Long companyId, List<Long> assetIds);
	
	Page<WorkOrder> query(Long companyId, String start, String end, String keyword, Pageable pageable);
	
	@Transactional
	long setWorkers(Long id, List<String> usernames);
	
	@Transactional
	long complete(Long id, String note);
	
	@Transactional
	long delete(Long id);
	
	Page<WorkOrder> queryByUsername(String username, String start, String end, Pageable pageable);
	
	@Transactional
	long syncHistory(ArrayList<AssetHistoryItem> items);
}
