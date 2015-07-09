package com.ymt360.mongodb;

import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class MongoUtil implements IMongoUtil {

	private Mongo mgInst;
	private DB mgDBInst;
	private DBCollection mgTBInst;
	public MongoUtil(){}
	public MongoUtil(String url,int port){
		try {
			this.mgInst=new Mongo(url,port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MongoUtil(String url,int port,String dbname){
		try {
			this.mgInst=new Mongo(url,port);
			this.mgDBInst = mgInst.getDB(dbname);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MongoUtil(String url,int port,String dbname,String table){
		try {
			this.mgInst=new Mongo(url,port);
			this.mgDBInst = mgInst.getDB(dbname);
			this.mgTBInst = mgDBInst.getCollection(table);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void save(String table,JSONObject jo) {
		// TODO Auto-generated method stub
		DBObject object = (DBObject)JSON.parse(jo.toString());
		mgTBInst.save(object);
	}
	@Override
	public void save(JSONObject jo) {
		// TODO Auto-generated method stub
		DBObject object = (DBObject)JSON.parse(jo.toString());
		mgTBInst.save(object);
	}
	@Override
	public JSONObject findByKey(String key,Object value) {
		BasicDBObject query = new BasicDBObject();
        query.put(key, value);
        DBObject found = mgTBInst.findOne(query);
        return new JSONObject(JSON.serialize(found));
	}
	@Override
	public void saveBatch(String table,JSONArray ja) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String table,String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String table,String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printAll(){
		 //查询所有的数据
        DBCursor cur = mgTBInst.find();
        while (cur.hasNext()) {
            System.out.println(cur.next());
        }
	}
	public Mongo getMgInst() {
		return mgInst;
	}
	public void setMgInst(Mongo mgInst) {
		this.mgInst = mgInst;
	}
	public DB getMgDBInst() {
		return mgDBInst;
	}
	public void setMgDBInst(String dbname) {
		this.mgDBInst = mgInst.getDB(dbname);
		this.mgDBInst = mgDBInst;
	}
	public DBCollection getMgTBInst() {
		return mgTBInst;
	}
	public void setMgTBInst(String table) {
		this.mgTBInst = this.mgDBInst.getCollection(table);
	}

}
