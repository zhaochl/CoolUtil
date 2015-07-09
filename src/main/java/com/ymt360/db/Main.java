package com.ymt360.db;

public class Main {

	public static void main(String[] args) {
		String jdbc_db = "ymt360";
		String jdbc_driver = "com.mysql.jdbc.Driver";
		//earth
		String jdbc_url = "jdbc:mysql://10.10.40.212:3306/ymt360?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
		//dev
		//jdbc_url=jdbc:mysql://120.132.48.223:3306/ymt360?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false
		String jdbc_username = "ecun";
		String jdbc_password = "ecun001";
		Db dbInst = new Db(jdbc_db, jdbc_driver, jdbc_url, jdbc_username,
				jdbc_password);
		System.out.println(dbInst);
	}
}
