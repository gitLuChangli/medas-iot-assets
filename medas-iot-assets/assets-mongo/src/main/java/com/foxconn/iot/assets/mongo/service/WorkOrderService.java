package com.foxconn.iot.assets.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foxconn.iot.assets.mongo.document.WorkOrder;

public interface WorkOrderService {
	
	void create(Long companyId, List<Long> assetIds);
	
	Page<WorkOrder> query(Long companyId, Pageable pageable);
}
