package com.zcl.db;

public class Main {

	public static void main(String[] args) {
		String jdbc_db = "test_db";
		String jdbc_driver = "com.mysql.jdbc.Driver";
		//earth
		String jdbc_url = "jdbc:mysql://localhost:3306/test_db?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
		//dev
		String jdbc_username = "root";
		String jdbc_password = "root001";
		Db dbInst = new Db(jdbc_db, jdbc_driver, jdbc_url, jdbc_username,
				jdbc_password);
		System.out.println(dbInst);
	}
}
