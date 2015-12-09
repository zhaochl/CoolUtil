package com.zcl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	
	public static void main(String[] args) {
		Long time = new Long(445555555);
		String date = DateUtil.toDateString10(time);
		System.out.println(date);
		
		Date datess = DateUtil.toDate(time);
		System.out.println(datess);
		
		String timeStr = "2015-06-06 11:45:55";
		long timeStamp = DateUtil.toTimeStamp10(timeStr);
		System.out.println(timeStamp);
		
		String timeStr2 ="2015-06-29";
		String pattern = "yyyy-MM-dd";
		long timeStamp2 = DateUtil.toTimeStamp10(timeStr2,pattern);
		System.out.println(timeStamp2);
	}
	
}
