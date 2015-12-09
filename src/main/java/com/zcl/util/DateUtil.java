package com.zcl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * 
 * @author zcl
 * @date 2015.6.2
 */
public class DateUtil {

	public static String toDateString10(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(time*1000);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	public static String toDateString13(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	public static Date toDate(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static long toTimeStamp13(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime();
	}
	public static long toTimeStamp10(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime()/1000;
	}
	
	public static String toDateString(long time,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	public static Date toDate(long time,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static long toTimeStamp13(String time,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime();
	}
	public static long toTimeStamp10(String time,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime()/1000;
	}
	
	public static String today() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		Date d = new Date();
		return format.format(d);
	}

	public static String yesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal
				.getTime());
//		System.out.println(yesterday);
		return yesterday;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.today());
		System.out.println(DateUtil.yesterday());
		
		Long time = new Long(1433562355000l);
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
