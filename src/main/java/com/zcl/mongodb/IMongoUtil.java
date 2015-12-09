package com.zcl.mongodb;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IMongoUtil {

	public void save(String table,JSONObject jo);
	public void saveBatch(String table,JSONArray ja);
	public void update(String table,String key);
	public void remove(String table,String key);
	JSONObject findByKey(String key, Object value);
	void save(JSONObject jo);
	void printAll();
}
