package com.foxconn.iot.file.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.foxconn.iot.file.template.AssetsTemplate;

public class UploadAssetsListener extends AnalysisEventListener<AssetsTemplate> {

	private static final Logger LOGGER =
	        LoggerFactory.getLogger(UploadAssetsListener.class);
	
	private List<AssetsTemplate> list = new ArrayList<>();
	
	private HttpServletResponse response;
	
	public UploadAssetsListener(HttpServletResponse response) {
		this.response = response;
	}
	
	@Override
	public void invoke(AssetsTemplate data, AnalysisContext context) {
		LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
        LOGGER.info("所有数据解析完成！");
        Map<String, Object> result = new HashedMap<>();
        result.put("code", 200);
        result.put("message", "上传成功");
        result.put("data", list);
        response.setContentType("application/json;charset=UTF-8");
        try {
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
