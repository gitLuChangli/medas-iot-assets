package com.foxconn.iot.assets.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.foxconn.iot.assets.mongo.dao.AssetEpcPrefixDao;
import com.foxconn.iot.assets.mongo.document.AssetEpcPrefix;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Component
public class AssetEpcPrefixDaoImpl implements AssetEpcPrefixDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(AssetEpcPrefix prefix) {
		mongoTemplate.insert(prefix);
	}
	
	@Override
	public Long update(AssetEpcPrefix prefix) {
		Update update = new Update();
		update.set("prefix", prefix.getPrefix());
		update.set("value", prefix.getValue());
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(prefix.getId()));
		UpdateResult result = mongoTemplate.updateFirst(query, update, AssetEpcPrefix.class);
		return result.getModifiedCount();
	}
	
	@Override
	public long delete(Long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		DeleteResult result = mongoTemplate.remove(query);
		return result.getDeletedCount();
	}

	@Override
	public List<AssetEpcPrefix> query(Long companyId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("companyId").is(companyId));		
		return mongoTemplate.find(query, AssetEpcPrefix.class);
	}
}
