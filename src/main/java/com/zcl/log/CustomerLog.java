package com.zcl.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

public class CustomerLog {

	/**
	 * @author zcl
	 * @descrption 自定义的LogLevel
	 */
	private static class CustomerLogLevel extends Level {
		public CustomerLogLevel(int level, String levelStr, int syslogEquivalent) {
			super(level, levelStr, syslogEquivalent);
		}
	}

	/**
	 * 自定义级别名称，以及级别范围
	 */
	private static final Level CustomerLevel = new CustomerLogLevel(20050,
			"CUSTOMER", SyslogAppender.LOG_LOCAL0);

	/**
	 * @param logger
	 * @param objLogInfo
	 */
	public static void customerLog(Logger logger, Object objLogInfo) {
		logger.log(CustomerLevel, objLogInfo);
	}

}
