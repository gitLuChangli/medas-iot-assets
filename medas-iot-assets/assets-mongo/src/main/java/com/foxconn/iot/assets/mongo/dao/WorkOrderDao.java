package com.foxconn.iot.assets.mongo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foxconn.iot.assets.mongo.document.WorkOrder;

public interface WorkOrderDao {
	
	void create(WorkOrder wo);
	
	Page<WorkOrder> query(Long companyId, Pageable pageable);
	
	long count(Long companyId);
}
