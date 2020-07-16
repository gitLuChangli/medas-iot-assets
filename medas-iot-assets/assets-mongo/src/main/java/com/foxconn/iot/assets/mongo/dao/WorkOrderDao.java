package com.foxconn.iot.assets.mongo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foxconn.iot.assets.mongo.document.WorkOrder;

public interface WorkOrderDao {
	
	void create(WorkOrder wo);
	
	Page<WorkOrder> query(Long companyId, String start, String end, String keyword, Pageable pageable);
	
	long count(Long companyId, String start, String end, String keyword);
	
	long setWorkers(Long id, List<String> usernames);
	
	long complete(Long id, String note);	
	
	long delete(Long id);
	
	Page<WorkOrder> queryByUsername(String username, String start, String end, Pageable pageable);
	
	long countByUsername(String username, String start, String end);
}
