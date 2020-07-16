package com.foxconn.iot.assets.common.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date getDate(String ymd) throws ParseException {
		return sdf.parse(ymd);
	}
}
