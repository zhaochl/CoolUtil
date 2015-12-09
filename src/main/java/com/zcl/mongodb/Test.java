package com.zcl.mongodb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		MongoUtil mu = new MongoUtil("120.132.57.216",27017,"esMonitor","user");
		JSONObject jo = new JSONObject();
		/*jo.put("username", "test");
		jo.put("password", "test");
		mu.save("user", jo);*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Date d = new Date();
		System.out.println(d.toLocaleString());
		System.out.println(d.toGMTString());
		String date = format.format(d);
		System.out.println(date);
//		TimeStamp t = new TimeStamp(l);
		
		jo = mu.findByKey("username", "test");
		System.out.println(jo.toString());
		System.out.println("success");
		mu.printAll();
	}
}
