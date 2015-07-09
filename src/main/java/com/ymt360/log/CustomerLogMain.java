package com.ymt360.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ymt360.main.App;


public class CustomerLogMain {
	private static  Logger log = LogManager.getLogger(CustomerLogMain.class);
	public static void main(String[] args) {
		CustomerLog.customerLog(log, "自定义日志级别");
		log.info("哈哈哈哈");
	}
}
