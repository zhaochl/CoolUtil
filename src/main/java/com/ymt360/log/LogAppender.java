package com.ymt360.log;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;
/**
 * 
 * @author zcl
 * @date 2015/07/09
 * @description 自定义log_level的logAppendar
 *  实现单种log_level写入单个log文件
 * @company ymt.Inc
 */
public class LogAppender extends DailyRollingFileAppender {
	 
	 @Override
	 public boolean isAsSevereAsThreshold(Priority priority) {
	  return this.getThreshold().equals(priority);
	 }
	}
