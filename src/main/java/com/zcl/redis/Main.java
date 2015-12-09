package com.zcl.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		RedisUtil ru = new RedisUtil("123.59.55.237",6379);
//		ru.setRedisData("test", "test");
//		ru.addRedisList("testList", "a");
//		ru.addRedisList("testList", "b");
		List dataList = ru.getRedisList("testList");
		for(int i=0;i<dataList.size();i++){
			String val = (String) dataList.get(i);
			System.out.println(val);
		}
		
		List dataList1 = ru.getRedisList("testList1");
		System.out.println(dataList1.size());
		ru.addRedisSet("set", "a");
		ru.addRedisSet("set", "a");
		ru.addRedisSet("set", "b");
		Set <String> set = ru.getRedisSet("set");
		Iterator it = set.iterator();
		while(it.hasNext()){
			String val = (String) it.next();
			System.out.println(val);
		}
	}
}
