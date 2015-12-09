package com.zcl.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;




public class DbMgr {

	/**
	 * @param args
	 */
	private Connection conn;//设置SQL语句
	private String sql;//设置查询条件
	private String params[];//设置参数
	private ResultSet queryResult;//查询结果
	private int updateResult;
	private Db dbInstance;
	public DbMgr(){//构造函数中已经构造连接
		try{
			//Db db = new Db();
			//Class.forName("com.mysql.jdbc.Driver");
			//this.conn=DriverManager.getConnection(db.getDburl(),db.getDbuser(),db.getDbpassword());
			
			if(this.dbInstance==null) {
				throw new Exception("DbMgr not initial with a db,use setter Method to set one");
			}
			this.conn = this.dbInstance.getDbconn();
			conn.setAutoCommit(false);
			//System.out.println("create DbManager success");
		}catch(Exception e){
			System.out.println("create DbManager fail");
			e.printStackTrace();
		}
	}
	public DbMgr(Db db){
		try{
			this.dbInstance = db;
			if(this.dbInstance==null) {
				
				throw new Exception("DbMgr not initial with a db,use setter Method to set one");
			}
			this.conn = this.dbInstance.getDbconn();
			conn.setAutoCommit(false);
			//System.out.println("create DbManager success");
		}catch(Exception e){
			System.out.println("create DbManager fail");
			e.printStackTrace();
		}
	}
	public DbMgr(String host,String dbname,String username,String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://"+host+":3306/"+dbname+"?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
			this.conn = DriverManager.getConnection(dburl,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setSql(String sql){
		this.sql=sql;
	}
	public String getSql(){
		return sql;
	}
	public Db getDbInstance() {
		return dbInstance;
	}
	public void setDbInstance(Db dbInstance) {
		this.dbInstance = dbInstance;
	}
	public void setParams(String[]params){
		this.params=params;
	}
	public String[]getParams(){
		return this.params;
	}

	public ResultSet getQueryResult(){	//获取查询结果
		try{
			PreparedStatement ps=conn.prepareStatement(sql, java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);//setString从第一列开始
				}
			}
			queryResult=ps.executeQuery();//执行查询，并将结果返回
			return queryResult;
		}catch(Exception e){
			//System.out.println("Query fail;error:"+e.toString());
			return null;
			/*try {
				throw new Exception("Query fail");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			//e.printStackTrace();
		}
	}	
	public int getUpdateResult(){//插入数据
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			updateResult=ps.executeUpdate();//执行新增数据库操作
			ps.close();
			conn.commit();//确认提交
			//conn.close();
		}catch(Exception e){
			System.out.println("变更数据失败");
			e.printStackTrace();
			try{
				conn.rollback();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return updateResult;
	}
	
	public void deleteDbManager(){
		try{
			if(this.queryResult!=null){
				if(!this.queryResult.isClosed()){
					this.queryResult.close();
				}
			}
			if(this.conn!=null){
				if(!this.conn.isClosed()){
					this.conn.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main1(String args[]) throws SQLException{
		
		System.out.println("Usage:java -jar data2es.jar hostname dbname");
		System.out.println("default for dev-localhost-test-db");
		
		List <Tb> dbInfo = new ArrayList<Tb>();
		Db db = null;
		String host ="localhost";
		String dbname="test_db";
		if(args.length==0) {
			db = new Db();
			System.out.println(db);
		}else{
			host = args[0];
			dbname = args[1];
			System.out.println("host:"+host+",dbname:"+dbname);
			String dbdriver = "com.mysql.jdbc.Driver";
			String dburl = "jdbc:mysql://"+host+":3306/"+dbname;
			String dbuser = "root";
			String dbpassword = "root001";
			db = new Db(dbname,dbdriver,dburl,dbuser,dbpassword);
			System.out.println(db);
		}
		DbMgr dm = new DbMgr(db);
		dm.setDbInstance(db);
		String [] tables =db.getTbs();
		//System.out.println("count:"+tables.length);
		
		for(int i=0;i<tables.length;i++){
			String table = tables[i];
			String sql = "desc "+table;
			dm.setSql(sql);
			ResultSet rs = dm.getQueryResult();
			Tb tb = new Tb();
			tb.setTbName(table);
			while (rs.next()){
				//System.out.println(rs.getString(1)+":"+rs.getString(2));
				HashMap<String, String> fields = new HashMap<String,String>();
				//fields.put(rs.getString(1), rs.getString(2));
				//tb.setFields(fields);
				tb.putFields(rs.getString(1), rs.getString(2));
			}
			dbInfo.add(tb);
		}
		
/*		StringBuffer sb = new StringBuffer();
		
		//System.out.println("size:"+dbInfo.size());
		List sqlList= new ArrayList<String>();
		for(int i=0;i<dbInfo.size();i++) {
			//System.out.println(i);
			Tb t = dbInfo.get(i);
			Map fields = t.getFields();
			Iterator it = fields.keySet().iterator();
			while(it.hasNext()) {
				String field = (String) it.next();
				String type = (String) fields.get(field);
				if(field.contains("customer_id")) {
					//System.out.println("table:"+t.getTbName()+",field:"+field+",type:"+val);
					sb = SqlBuilder.getSqlBuffer_View_Tpl(dbname,t.getTbName(), field, type);
//					System.out.println(sb.toString());
					sqlList.add(sb.toString());
				}
			}
		}
		for(int i=0;i<sqlList.size();i++) {
			String sql = (String) sqlList.get(i);
			System.out.println(sql);
		}*/
		String sql ="SELECT cu.id AS offset_id,cu.* from test_db.users_app cu limit 1,10";
		
		dm.setSql(sql);
		ResultSet rs = dm.getQueryResult();
		
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();
		for (int i = 1; i <= num; i++) {
			//System.out.println(md.getColumnName(i));
			System.out.println(md.getColumnLabel(i));
		}
		while(rs.next()){
			//System.out.println(rs.getString(1));
			//rs.getString(1)==rs.getInt(1)
			//rs.getString(0) error
		}
		//JSONArray ja = RSUtil.convertRsToJSONArray(rs);
		//System.out.println(ja.toString());
	}
	public static void main(String[] args) {
		DbMgr dm = new DbMgr("localhost","test","root","root001");
		System.out.println(dm);
	}
}