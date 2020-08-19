package com.foxconn.iot.assets.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.EasyExcel;
import com.foxconn.iot.assets.common.api.CommonPage;
import com.foxconn.iot.assets.common.api.CommonResult;
import com.foxconn.iot.assets.component.IAuthenticationFacade;
import com.foxconn.iot.assets.model.UmsAdmin;
import com.foxconn.iot.assets.mongo.dao.dto.AssetHistoryItem;
import com.foxconn.iot.assets.mongo.document.Asset;
import com.foxconn.iot.assets.mongo.document.WorkOrder;
import com.foxconn.iot.assets.mongo.document.WorkOrderItem;
import com.foxconn.iot.assets.service.AssetInventoryService;
import com.foxconn.iot.assets.service.UmsAdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "资产接口" }, value = "AssetInventoryController")
public class AssetInventoryController {

	@Autowired
	private AssetInventoryService assetInventoryService;
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private UmsAdminService adminService;

	/**
	 * 保存资产
	 * 
	 * @param companyId
	 * @param assets
	 * @return
	 */
	private CommonResult<?> saveAssets(Long companyId, List<Asset> assets) {
		if (assets.size() == 0) return CommonResult.failed();
		long count = assetInventoryService.saveAssets(companyId, assets);
		if (count > 0)
			return CommonResult.success(null);
		else
			return CommonResult.failed();
	}

	@ApiOperation(value = "保存资产")
	@PostMapping(value = "/api/admin2/asset/")
	public CommonResult<?> save(@RequestBody List<Asset> assets) {		
		return saveAssets(authenticationFacade.getCompanyId(), assets);
	}

	@ApiOperation(value = "系统管理员保存资产")
	@PostMapping(value = "/api/admin/asset/")
	public CommonResult<?> save(@RequestParam(value = "company", required = true) Long companyId,
			@RequestBody List<Asset> assets) {
		return saveAssets(companyId, assets);
	}

	/**
	 * 查询固定资产
	 * 
	 * @param companyId
	 * @param pageable
	 * @return
	 */
	private CommonResult<?> queryAssets(Long companyId, Pageable pageable, String keyword) {
		Page<Asset> assets = assetInventoryService.queryAssets(companyId, pageable, keyword);
		return CommonResult.success(CommonPage.restPage(assets));
	}

	@ApiOperation(value = "查询固定资产")
	@GetMapping(value = "/api/admin2/asset/")
	public CommonResult<?> query(@PageableDefault Pageable pageable, @RequestParam(value = "keyword", required = false) String keyword) {
		return queryAssets(authenticationFacade.getCompanyId(), pageable, keyword);
	}

	@ApiOperation(value = "查询固定资产")
	@GetMapping(value = "/api/admin/asset/")
	public CommonResult<?> query(@RequestParam(value = "company", required = true) Long companyId,
			@PageableDefault Pageable pageable, @RequestParam(value = "keyword", required = false) String keyword) {
		return queryAssets(companyId, pageable, keyword);
	}

	/**
	 * 创建工单
	 * 
	 * @param companyId
	 * @param assetIds
	 * @return
	 */
	private CommonResult<?> createWorkOrder(Long companyId, List<Long> assetIds) {
		assetInventoryService.createWorkOrder(companyId, assetIds);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "创建盘点工单")
	@PostMapping(value = "/api/admin2/asset/inventory/")
	public CommonResult<?> createInventory(@RequestParam(value = "assetIds") List<Long> assetIds) {
		return createWorkOrder(authenticationFacade.getCompanyId(), assetIds);
	}

	@ApiOperation(value = "系统管理员创建盘点工单")
	@PostMapping(value = "/api/admin/asset/inventory/")
	public CommonResult<?> createInventory(@RequestParam(value = "company", required = true) Long companyId,
			@RequestParam(value = "assetIds") List<Long> assetIds) {
		return createWorkOrder(companyId, assetIds);
	}

	/**
	 * 查询部门所有工单
	 * 
	 * @param companyId
	 * @param start
	 * @param end
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	private CommonResult<?> queryWorkOrder(Long companyId, String start, String end, String keyword,
			Pageable pageable) {
		Page<WorkOrder> wos = assetInventoryService.queryWorkOrders(companyId, start, end, keyword, pageable);
		return CommonResult.success(CommonPage.restPage(wos));
	}

	@ApiOperation(value = "查询工单")
	@GetMapping(value = "/api/admin2/asset/orders/")
	public CommonResult<?> query(@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "keyword", required = false) String keyword, @PageableDefault Pageable pageable) {
		return queryWorkOrder(authenticationFacade.getCompanyId(), start, end, keyword, pageable);
	}

	@ApiOperation(value = "系统管理员查询部门工单")
	@GetMapping(value = "/api/admin/asset/orders/")
	public CommonResult<?> query(@RequestParam(value = "company", required = true) Long companyId,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "keyword", required = false) String keyword, @PageableDefault Pageable pageable) {
		return queryWorkOrder(companyId, start, end, keyword, pageable);
	}

	/**
	 * 设置盘点人员
	 * 
	 * @param companyId
	 * @param workId
	 * @param usernames
	 * @return
	 */
	private CommonResult<?> setInventoryWorkers(Long companyId, Long workId, List<String> usernames) {
		long count = assetInventoryService.setInventoryWorkers(companyId, workId, usernames);
		if (count > 0)
			return CommonResult.success(null);
		else
			return CommonResult.failed();
	}

	@ApiOperation(value = "分配盘点人")
	@PutMapping(value = "/api/admin2/asset/set/worker/{id:\\d+}")
	public CommonResult<?> setWorkers(@PathVariable(value = "id") Long id,
			@RequestParam(value = "usernames") List<String> usernames) {
		return setInventoryWorkers(authenticationFacade.getCompanyId(), id, usernames);
	}

	@ApiOperation(value = "系统管理员分配盘点人")
	@PutMapping(value = "/api/admin/asset/set/worker/{id:\\d+}")
	public CommonResult<?> setWorkers(@RequestParam(value = "company", required = true) Long companyId,
			@PathVariable(value = "id") Long id, @RequestParam(value = "usernames") List<String> usernames) {
		return setInventoryWorkers(companyId, id, usernames);
	}

	/**
	 * 结单
	 * 
	 * @param companyId
	 * @param workId
	 * @param note
	 * @return
	 */
	private CommonResult<?> completeWorkOrder(Long companyId, Long workId, String note) {
		long count = assetInventoryService.complete(companyId, workId, note);
		if (count > 0)
			return CommonResult.success(null);
		else
			return CommonResult.failed();
	}

	@ApiOperation(value = "结单")
	@PutMapping(value = "/api/admin2/asset/complete/{id:\\d+}")
	public CommonResult<?> complete(@PathVariable(value = "id") Long id, @RequestParam(value = "note") String note) {
		return completeWorkOrder(authenticationFacade.getCompanyId(), id, note);
	}

	@ApiOperation(value = "系统管理员结单")
	@PutMapping(value = "/api/admin/asset/complete/{id:\\d+}")
	public CommonResult<?> complete(@RequestParam(value = "company", required = true) Long companyId,
			@PathVariable(value = "id") Long id, @RequestParam(value = "note") String note) {
		return completeWorkOrder(companyId, id, note);
	}

	/**
	 * 删除工单
	 * 
	 * @param companyId
	 * @param workId
	 * @return
	 */
	private CommonResult<?> deleteWorkOrder(Long companyId, Long workId) {
		long count = assetInventoryService.deleteWorkOrder(companyId, workId);
		if (count > 0)
			return CommonResult.success(null);
		else
			return CommonResult.failed();
	}

	@ApiOperation(value = "删除工单")
	@DeleteMapping(value = "/api/admin2/asset/work/{id:\\d+}")
	public CommonResult<?> delete(@PathVariable(value = "id") Long id) {
		return deleteWorkOrder(authenticationFacade.getCompanyId(), id);
	}

	@ApiOperation(value = "系统管理员删除工单")
	@DeleteMapping(value = "/api/admin/asset/work/{id:\\d+}")
	public CommonResult<?> delete(@RequestParam(value = "company", required = true) Long companyId,
			@PathVariable(value = "id") Long id) {
		return deleteWorkOrder(companyId, id);
	}

	/**
	 * 用户查询盘点工单
	 * 
	 * @param username
	 * @param start
	 * @param end
	 * @param pageable
	 * @return
	 */
	@ApiOperation(value = "用户查询盘点工单")
	@GetMapping(value = "/api/user/asset/orders/")
	public CommonResult<?> queryByUsername(@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end, @PageableDefault Pageable pageable) {
		Page<WorkOrder> wos = assetInventoryService.queryWorkOrdersByUsername(authenticationFacade.getUsername(), start, end, pageable);
		return CommonResult.success(CommonPage.restPage(wos));
	}

	@ApiOperation(value = "用户同步盘点日志")
	@PostMapping(value = "/api/user/asset/sync/history")
	public CommonResult<?> syncHistory(@RequestBody ArrayList<AssetHistoryItem> items) {
		long count = assetInventoryService.syncHistory(items);
		if (count > 0) {
			return CommonResult.success(null);
		} else {
			return CommonResult.failed();
		}
	}
	
	/**
	 * 查看所在部门的用户
	 * 
	 * @param companyId
	 * @return
	 */
	private CommonResult<?> queryCompanyUsers(Long companyId) {
		List<UmsAdmin> users = adminService.queryByCompany(companyId);
		return CommonResult.success(users);
	}
	
	@ApiOperation(value = "查看所在部门的用户")
	@GetMapping(value = "/api/admin2/company/users/")
	public CommonResult<?> queryUsers() {
		return queryCompanyUsers(authenticationFacade.getCompanyId());
	}
	
	@ApiOperation(value = "查看所在部门的用户")
	@GetMapping(value = "/api/admin/company/users/")
	public CommonResult<?> queryUsers(@RequestParam(value = "company", required = true) Long companyId) {
		return queryCompanyUsers(companyId);
	}
	
	private void downloadWorkOrder(HttpServletResponse response, Long companyId, Long workId) throws IOException {
		WorkOrder workOrder = assetInventoryService.queryWorkOrder(companyId, workId);
		response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + workOrder.getNum() + ".xlsx");
        EasyExcel.write(response.getOutputStream(), WorkOrderItem.class).sheet(workOrder.getNum()).doWrite(workOrder.getItems());
	}
	
	@ApiOperation(value = "导出工单盘点情况")
	@GetMapping(value = "/api/admin/asset/download/work/{id:\\d+}")
	public void downloadWork(@PathVariable(value = "id") Long id, @RequestParam(value = "company", required = true) Long companyId, HttpServletResponse response) throws IOException {
		downloadWorkOrder(response, companyId, id);
	}
	
	@ApiOperation(value = "导出工单盘点情况")
	@GetMapping(value = "/api/admin2/asset/download/work/{id:\\d+}")
	public void downloadMyWork(@PathVariable(value = "id") Long id, HttpServletResponse response) throws IOException {
		downloadWorkOrder(response, authenticationFacade.getCompanyId(), id);
	}
}
