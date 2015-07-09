package com.ymt360.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class RSUtil {

	/**
	 * 通用取结果方案,返回JSONArray
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public synchronized static JSONArray convertRsToJSONArray(ResultSet rs)
			throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();
		JSONArray array = new JSONArray();
		while (rs.next()) {
			JSONObject mapOfColValues = new JSONObject();
			for (int i = 1; i <= num; i++) {
				// mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				mapOfColValues.put(md.getColumnLabel(i), rs.getObject(i));
			}
			array.put(mapOfColValues);
		}
		return array;
	}
	public synchronized static String convertRsToCSV(ResultSet rs){
		
		StringBuffer sb = new StringBuffer();
		try {
			ResultSetMetaData md = rs.getMetaData();
			
			int num = md.getColumnCount();
			for (int i = 1; i <= num; i++) {
				String columnName = md.getColumnLabel(i);
				//System.out.println("i:"+columnName);
				sb.append(columnName);
				if(i<=num-1){
					sb.append(",");
				}
			}
			sb.append("\n");
			while(rs.next()){
				//String date = rs.getString(1);
				//int productID =rs.getInt(2);
//				System.out.println("date:"+date+",productID:"+productID);
				for (int i = 1; i <= num; i++) {
					sb.append(rs.getObject(i));
					if(i<=num-1){
						sb.append(",");
					}
					if(i==num){
						sb.append("\n");
					}
				}
			}
			System.out.println(sb.toString());
//			System.out.println(cu.getFullFileNamePath());
//			cu.append(sb.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
