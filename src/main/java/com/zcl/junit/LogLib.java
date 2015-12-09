package com.zcl.junit;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
/**
 * 
 * @author zcl
 * @date 2015/07/09
 * @description Log 基础库辅助类
 */
public class LogLib {

	private static Random random = new Random();
	/**
	 * 生成LogID
	 * @return String
	 */
	public static String getLogID(){
		long logid0_3 = System.currentTimeMillis()/1000%3600;
		long logid4_7 = System.currentTimeMillis()%1000;
		long logid8_10 = random.nextLong()%1000;
		long logid11_14 = getLocalServerHash() * random.nextLong()%10000;
		String Strlogid11_14 =Long.toString(logid11_14);
		String logid = fix(logid0_3,4)+fix(logid4_7,3)+fix(logid8_10,3)+fix(Long.valueOf(Strlogid11_14),4);
		return logid;
	}
	/**
	 * 
	 * @param l
	 * @param len
	 * @return 为logid的各段生成函数进行修正，不足len位，左位补0，负数取反
	 */
	public static String fix(long l,int len){
		if(l<0){
			l = -l;
		}
		int toFix = len - Long.valueOf(l).toString().length();
		if(toFix > 0){
			String fixStr = "";
			switch(toFix){
				case 1:
					fixStr = "0";break;
				case 2:
					fixStr = "00";break;
				case 3:
					fixStr = "000";break;
				case 4:
					fixStr = "0000";break;
			}
			return fixStr+Long.valueOf(l).toString();
		} else if(toFix<0){
			return Long.valueOf(l).toString().substring(0,len);
		} else {
			
			return Long.valueOf(l).toString();
		} 
	}
	/**
	 * 获取Server的hash值
	 * @return
	 */
	public static long getLocalServerHash(){
		long result=0l;
		InetAddress s;
		try {
			s = InetAddress.getLocalHost();
			String ip= s.getHostAddress();
			if(!ip.equals("")){
				result = Hash(ip);
			}else{
				result = Hash("127.0.0.1");
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * ELF-Hash算法
	 */
	public static int Hash(String str) {
		int b = 378551;
		int a = 63689;
		int hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = hash * a + str.charAt(i);
			a = a * b;
		}

		return (hash & 0x7FFFFFFF);
	}
	
}
