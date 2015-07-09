package com.ymt360.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteUtil implements ISqliteUtil {

	private String fileName;
	private Connection conn;
	private Statement stat;
	public SqliteUtil(String fileName){
		try {
			this.fileName= fileName;
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			this.stat = this.conn.createStatement();
			//this.conn.setAutoCommit(false);
			checkInit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void checkInit(){
		//if(!new File("test.db").exists()){
		if(new File(this.fileName).length()==0||!new File(this.fileName).exists()){
			try {
				System.out.println("conn:"+conn+"stat:"+stat);
				int result = stat.executeUpdate("create table redis (rkey, rvalue);");
				System.out.println("fuck---not exits,run result:"+result);
				//conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
//			System.out.println("fuck-----exits");
		}
	}
	@Override
	public void setData(String key, String value) {
		// TODO Auto-generated method stub
		try {
			 //update redis set rvalue = 'test' where rkey='Gandhi'
			if(!getData(key).equals("")) {
				String sql="update redis set rvalue='"+value+"' where rkey='"+key+"'";
				//Statement ps=conn.createStatement();
				int result = stat.executeUpdate(sql);
			}else{
				String sql="insert into redis (rkey,rvalue) values('"+key+"','"+value+"')";
				//Statement ps=conn.createStatement();
				int result = stat.executeUpdate(sql);
			}
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getData(String key) {
		// TODO Auto-generated method stub
		ResultSet rs;
		String value="";
		String sql ="select  rvalue from redis where rkey='"+key+"' limit 1;";
		System.out.println("sqlite-sql:"+sql);
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			//conn.commit();
			while (rs.next()) {
				value = rs.getString("rvalue");
				System.out.println("rkey = " + rs.getString("rvalue"));
			}
			 rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public void shutDown() {
		if (stat != null) {
			try {
				if (!stat.isClosed()) {
					stat.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (getConn() != null) {

			try {
				if (!getConn().isClosed()) {
					getConn().close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean isConnected(){
		boolean result =false;
		try {
			result = !getConn().isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void connect() {
		// TODO Auto-generated method stub
		try {
			if(getConn().isClosed()){
				getConn();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		try {
			this.conn =DriverManager.getConnection("jdbc:sqlite:"+fileName);
			this.stat = this.conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
