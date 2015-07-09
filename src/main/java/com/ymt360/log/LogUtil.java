package com.ymt360.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogUtil {


	public synchronized static void log(String tag,
			String message) {

		Logger log = LogManager.getLogger(LogUtil.class);
		if (tag.equals("i")) {
			log.info(message);
		} else if (tag.equals("e")) {
			log.error(message);
		}
	}
	public static void main(String[] args) {
		LogUtil.log("i","some thing");
	}
}
