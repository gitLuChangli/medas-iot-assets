package com.foxconn.iot.assets.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.foxconn.iot.assets.mongo.dao.dto.AssetHistoryItem;
import com.foxconn.iot.assets.mongo.document.Asset;
import com.foxconn.iot.assets.mongo.document.WorkOrder;

public interface AssetInventoryService {

	/**
	 * 保存资产
	 * 
	 * @param companyId
	 * @param assets
	 * @return
	 */
	long saveAssets(Long companyId, List<Asset> assets);

	/**
	 * 查询部门资产
	 * 
	 * @param companyId
	 * @param pageable
	 * @param keyword
	 * @return
	 */
	Page<Asset> queryAssets(Long companyId, Pageable pageable, String keyword);

	/**
	 * 创建盘点工单
	 * 
	 * @param companyId
	 * @param assetIds
	 */
	void createWorkOrder(Long companyId, List<Long> assetIds);

	/**
	 * 查询盘点工单
	 * 
	 * @param companyId
	 * @param start
	 * @param end
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<WorkOrder> queryWorkOrders(Long companyId, String start, String end, String keyword, Pageable pageable);

	/**
	 * 设置盘点人员
	 * 
	 * @param companyId 部门编号
	 * @param workId    盘点工单编号
	 * @param usernames 盘点人工号
	 * @return
	 */
	@Transactional
	long setInventoryWorkers(Long companyId, Long workId, List<String> usernames);

	/**
	 * 完成盘点
	 * 
	 * @param companyId
	 * @param id
	 * @param note
	 * @return
	 */
	@Transactional
	long complete(Long companyId, Long id, String note);

	/**
	 * 删除工单
	 * 
	 * @param companyId
	 * @param id
	 * @return
	 */
	@Transactional
	long deleteWorkOrder(Long companyId, Long id);

	/**
	 * 查询个人工单
	 * 
	 * @param username
	 * @param start
	 * @param end
	 * @param pageable
	 * @return
	 */
	Page<WorkOrder> queryWorkOrdersByUsername(String username, String start, String end, Pageable pageable);

	/**
	 * 同步盘点记录
	 * 
	 * @param items
	 * @return
	 */
	@Transactional
	long syncHistory(ArrayList<AssetHistoryItem> items);

	/**
	 * 查看工单
	 * 
	 * @param companyId
	 * @param workId
	 * @return
	 */
	WorkOrder queryWorkOrder(Long companyId, Long workId);
}
