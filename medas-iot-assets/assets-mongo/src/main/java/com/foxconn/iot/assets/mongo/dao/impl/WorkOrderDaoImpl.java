package com.foxconn.iot.assets.mongo.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.foxconn.iot.assets.common.api.TimeUtil;
import com.foxconn.iot.assets.mongo.dao.WorkOrderDao;
import com.foxconn.iot.assets.mongo.document.WorkOrder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Component
public class WorkOrderDaoImpl implements WorkOrderDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void create(WorkOrder wo) {
		mongoTemplate.insert(wo);
	}

	@Override
	public Page<WorkOrder> query(Long companyId, String start, String end, String keyword, Pageable pageable) {
		Query query = new Query();
		query.addCriteria(Criteria.where("companyId").is(companyId));
		Date ds = null;
		Date de = null;
		if (!StringUtils.isEmpty(start)) {
			try {
				ds = TimeUtil.getDate(start);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(end)) {
			try {
				de = TimeUtil.getDate(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (ds != null && de != null) {
			query.addCriteria(Criteria.where("createTime").gte(ds).lte(de));
		} else {
			if (ds != null)
				query.addCriteria(Criteria.where("crateTime").gte(start));
			if (de != null)
				query.addCriteria(Criteria.where("createTime").lte(de));
		}
		if (!StringUtils.isEmpty(keyword)) {
			Pattern pattern = Pattern.compile(String.format("^.*%s.*$", keyword), Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("usernames").regex(pattern));
		}
		query.with(pageable);
		
		Sort sort = Sort.by(Direction.DESC, "createTime");
		query.with(sort);
		
		List<WorkOrder> wos = mongoTemplate.find(query, WorkOrder.class);;
		return new PageImpl<>(wos, pageable, count(companyId, start, end, keyword));
	}

	@Override
	public long count(Long companyId, String start, String end, String keyword) {
		Query query = new Query();
		query.addCriteria(Criteria.where("companyId").is(companyId));
		Date ds = null;
		Date de = null;
		if (!StringUtils.isEmpty(start)) {
			try {
				ds = TimeUtil.getDate(start);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(end)) {
			try {
				de = TimeUtil.getDate(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (ds != null && de != null) {
			query.addCriteria(Criteria.where("createTime").gte(ds).lte(de));
		} else {
			if (ds != null)
				query.addCriteria(Criteria.where("crateTime").gte(start));
			if (de != null)
				query.addCriteria(Criteria.where("createTime").lte(de));
		}
		if (!StringUtils.isEmpty(keyword)) {
			Pattern pattern = Pattern.compile(String.format("^.*%s.*$", keyword), Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("usernames").regex(pattern));
		}
		return mongoTemplate.count(query, WorkOrder.class);
	}

	@Override
	public long setWorkers(Long id, List<String> usernames) {
		Update update = new Update();
		update.set("usernames", usernames);
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		UpdateResult result = mongoTemplate.updateFirst(query, update, WorkOrder.class);
		return result.getModifiedCount();
	}

	@Override
	public long complete(Long id, String note) {
		Update update = new Update();
		update.set("status", 1);
		update.set("note", note);
		update.set("completeTime", new Date());
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		UpdateResult result = mongoTemplate.updateFirst(query, update, WorkOrder.class);
		return result.getModifiedCount();
	}
	
	@Override
	public long delete(Long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		DeleteResult result = mongoTemplate.remove(query, WorkOrder.class);
		return result.getDeletedCount();
	}
	
	@Override
	public Page<WorkOrder> queryByUsername(String username, String start, String end, Pageable pageable) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usernames").is(username));
		query.addCriteria(Criteria.where("status").is(0));
		Date ds = null;
		Date de = null;
		if (!StringUtils.isEmpty(start)) {
			try {
				ds = TimeUtil.getDate(start);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(end)) {
			try {
				de = TimeUtil.getDate(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (ds != null && de != null) {
			query.addCriteria(Criteria.where("createTime").gte(ds).lte(de));
		} else {
			if (ds != null)
				query.addCriteria(Criteria.where("crateTime").gte(start));
			if (de != null)
				query.addCriteria(Criteria.where("createTime").lte(de));
		}		
		query.with(pageable);
		
		Sort sort = Sort.by(Direction.DESC, "createTime");
		query.with(sort);
		
		List<WorkOrder> wos = mongoTemplate.find(query, WorkOrder.class);;
		return new PageImpl<>(wos, pageable, countByUsername(username, start, end));
	}
	
	@Override
	public long countByUsername(String username, String start, String end) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usernames").is(username));
		query.addCriteria(Criteria.where("status").is(0));
		Date ds = null;
		Date de = null;
		if (!StringUtils.isEmpty(start)) {
			try {
				ds = TimeUtil.getDate(start);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(end)) {
			try {
				de = TimeUtil.getDate(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (ds != null && de != null) {
			query.addCriteria(Criteria.where("createTime").gte(ds).lte(de));
		} else {
			if (ds != null)
				query.addCriteria(Criteria.where("crateTime").gte(start));
			if (de != null)
				query.addCriteria(Criteria.where("createTime").lte(de));
		}
		return mongoTemplate.count(query, WorkOrder.class);
	}

	@Override
	public long inventory(Long id, Long assetId, String username, String nickname, String completeTime, String note, String building, String floor, String XT) {
		Update update = new Update();
		update.set("items.$.status", 1);
		update.set("items.$.username", username);
		update.set("items.$.nickname", nickname);
		update.set("items.$.completeTime", completeTime);
		update.set("items.$.note", note);
		if (!StringUtils.isEmpty(building)) {
			update.set("items.$.building", building);
		}
		if (!StringUtils.isEmpty(floor)) {
			update.set("items.$.floor", floor);
		}
		if (!StringUtils.isEmpty(XT)) {
			update.set("items.$.xianti", XT);
		}
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id)).addCriteria(Criteria.where("items.asset.id").is(assetId));
		UpdateResult result = mongoTemplate.updateFirst(query, update, WorkOrder.class);
		return result.getModifiedCount();
	}
	
	@Override
	public long updateCounted(Long id) {		
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("_id").is(id)), 
				Aggregation.unwind("items"), 
				Aggregation.match(Criteria.where("items.status").is(1)),
				Aggregation.group("items.asset.id").count().as("counted"));
		
		AggregationResults<BasicDBObject> ar = mongoTemplate.aggregate(aggregation, "work_order", BasicDBObject.class);
		if (ar.getMappedResults() == null || ar.getMappedResults().size() == 0) {
			return 0;
		}
		long counted = ar.getMappedResults().get(0).getLong("counted");
		Update update = new Update();
		update.set("counted", counted);
		Query query2 = new Query();
		query2.addCriteria(Criteria.where("id").is(id));
		UpdateResult result = mongoTemplate.updateFirst(query2, update, WorkOrder.class);
		return result.getMatchedCount();
	}
}
