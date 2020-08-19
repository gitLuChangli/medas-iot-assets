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
	
	long complete(Long companyId, Long id, String note);	
	
	long delete(Long companyId, Long id);
	
	Page<WorkOrder> queryByUsername(String username, String start, String end, Pageable pageable);
	
	long countByUsername(String username, String start, String end);
	
	long inventory(Long id,  Long assetId, String username, String nickname, String completeTime, String note, String building, String floor, String XT);
	
	long updateCounted(Long id);
	
	WorkOrder query(Long companyId, Long workId);
}
