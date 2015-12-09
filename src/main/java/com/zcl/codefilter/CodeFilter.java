package com.zcl.codefilter;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.json.JSONObject;
/**
 * 
 * @author zcl
 * 
 */
public class CodeFilter {

	public static String filter(String s){
		String result ="";
		result = s.replace("\t", "EEE").replace("\n", "FFF").replace(",", "GGG");
		try {
			result = new String(result.getBytes("utf-8"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void filter(JSONObject jo){
		Iterator i = jo.keys();
		while(i.hasNext()){
			String key = (String) i.next();
			Object value = jo.get(key);
			if(value instanceof JSONObject){
				JSONObject jotmp = (JSONObject) value;
				filter(jotmp);
			}else if(value instanceof String){
				jo.put(key, value.toString().replace("\n","").replace("\t", "").replace(",", ""));
			}
		}
	}
	
	public boolean isEncoding(String s,String encoding){
		boolean result = false;
		try {
			result = s.equals(new String(s.getBytes(encoding), encoding));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String encodeString(String s,String encoding){
		String result = s;
		try {
			result = new String(s.getBytes(getEncoding(s)),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String getEncoding(String s){
		String result = "";
		try {
			if(isEncoding(s,"iso8859-1")){
				result = "iso8859-1";
			}else if(isEncoding(s,"utf-8")){
				result = "utf-8";
			}else if(isEncoding(s,"GB2312")){
				result = "GB2312";
			}else if(isEncoding(s,"GBK")){
				result="GBK";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println("中文");
//        System.out.println("中文".getBytes());
//        System.out.println("中文".getBytes("GB2312"));
//        System.out.println("中文".getBytes("ISO8859_1"));
//        System.out.println(new String("中文".getBytes()));
//        System.out.println(new String("中文".getBytes(), "GB2312"));
//        System.out.println(new String("中文".getBytes(), "ISO8859_1"));
		/*
        System.out.println(new String("中文".getBytes("GB2312")));
        System.out.println(new String("中文".getBytes("GB2312"), "GB2312"));
        System.out.println(new String("中文".getBytes("GB2312"), "ISO8859_1"));
        System.out.println(new String("中文".getBytes("ISO8859_1")));
        System.out.println(new String("中文".getBytes("ISO8859_1"), "GB2312"));
        System.out.println(new String("中文".getBytes("ISO8859_1"), "ISO8859_1"));
        
		CodeFilter cf = new CodeFilter();
		
		String s1 = new String("abc".getBytes("ISO8859-1"),"utf-8");
		String encoding1 = cf.getEncoding(s1);
		System.out.println(encoding1);//utf-8
		
		String s2 = new String("中文".getBytes("GBK"),"GBK");
		String encoding2 = cf.getEncoding(s2);
		System.out.println(encoding2);//utf-8
		
		String s3 = new String("中文".getBytes("UTF-8"),"GB2312");
		String encoding3 = cf.getEncoding(s3);
		System.out.println(encoding3);//utf-8
		
		String s4 = new String("中文".getBytes("iso8859_1"),"utf-8");
		String encoding4 = cf.getEncoding(s4);
		System.out.println(encoding4);//utf-8
*/        
		JSONObject jo = new JSONObject();
		jo.put("a", "ss\tadf");
		System.out.println(jo.toString());
		CodeFilter.filter(jo);
		System.out.println(jo.toString());
		
		JSONObject jotmp = new JSONObject();
		jotmp.put("b", "bbb\n\taa");
		jo.put("ob", jotmp);
		System.out.println(jo.toString());
		CodeFilter.filter(jo);
		System.out.println(jo.toString());
	}
}
