package com.foxconn.iot.file.service;

import java.util.List;

public interface AssetsService {
	
	void createCollection(String name);
	
	void deleteCollection(String name);
	
	void insertMany(String collection, List<Object> objects);
	
	List<Object> findMany(String collection);
}
