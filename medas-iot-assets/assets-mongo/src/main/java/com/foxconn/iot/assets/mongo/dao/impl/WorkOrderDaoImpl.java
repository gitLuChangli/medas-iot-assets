package com.foxconn.iot.assets.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.foxconn.iot.assets.mongo.dao.WorkOrderDao;
import com.foxconn.iot.assets.mongo.document.WorkOrder;

@Component
public class WorkOrderDaoImpl implements WorkOrderDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void create(WorkOrder wo) {
		mongoTemplate.insert(wo);
	}

	@Override
	public Page<WorkOrder> query(Long companyId, Pageable pageable) {
		Query query = new Query();
		query.addCriteria(Criteria.where("companyId").is(companyId));
		query.with(pageable);
		List<WorkOrder> wos = mongoTemplate.find(query, WorkOrder.class);
		return new PageImpl<>(wos, pageable, count(companyId));
	}

	@Override
	public long count(Long companyId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("companyId").is(companyId));
		return mongoTemplate.count(query, WorkOrder.class);
	}
}
