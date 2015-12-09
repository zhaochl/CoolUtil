package com.zcl.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

	public static String find(String str,String regx) {
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(str);
		String result ="";
		while(m.find()){
			result = m.group();
		}
		return result;
	}
	public static void main(String[] args) {
		String regx = "^[^:]*Exception|(org|java).([a-zA-z].+)[^:](Exception|Error){1}";//v3
		String str ="Caused by: org.elasticsearch.search.facet.FacetPhaseExecutionException: Facet [terms]: failed to find mapping for 交易金额";
		String causeRootExcetion = PatternUtil.find(str, regx);
		System.out.println(causeRootExcetion);
		
		String cnString = "abc中国人";
//		String cnRegx ="^[\u4E00-\u9FFF]+$";
		String cnRegx ="[\u4E00-\u9FFF]+";
		String cnStr  = PatternUtil.find(cnString, cnRegx);
		System.out.println(cnStr);
	}
}
