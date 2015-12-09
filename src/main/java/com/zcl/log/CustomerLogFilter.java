package com.zcl.log;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class CustomerLogFilter extends Filter {
	boolean acceptOnMatch = false;
	private String levelMin;
	private String levelMax;

	public String getLevelMin() {
		return levelMin;
	}

	public void setLevelMin(String levelMin) {
		this.levelMin = levelMin;
	}

	public String getLevelMax() {
		return levelMax;
	}

	public void setLevelMax(String levelMax) {
		this.levelMax = levelMax;
	}

	public boolean isAcceptOnMatch() {
		return acceptOnMatch;
	}

	public void setAcceptOnMatch(boolean acceptOnMatch) {
		this.acceptOnMatch = acceptOnMatch;
	}

	@Override
	public int decide(LoggingEvent lgEvent) {
		int inputLevel = lgEvent.getLevel().toInt();

		if (inputLevel >= getLevel(levelMin)
				&& inputLevel <= getLevel(levelMax)) {
			return 0;
		}

		return -1;
	}

	private int getLevel(String level) {
		level = level.toUpperCase();
		if (level.equals("CUSTOMER")) {
			return LevelType.CUSTOMER.getType();
		}
		if (level.equals("OFF")) {
			return LevelType.OFF.getType();
		}
		if (level.equals("FATAL")) {
			return LevelType.FATAL.getType();
		}
		if (level.equals("ERROR")) {
			return LevelType.ERROR.getType();
		}
		if (level.equals("INFO")) {
			return LevelType.INFO.getType();
		}
		if (level.equals("WARN")) {
			return LevelType.WARN.getType();
		}
		if (level.equals("DEBUG")) {
			return LevelType.DEBUG.getType();
		}
		if (level.equals("ALL")) {
			return LevelType.ALL.getType();
		}
		return LevelType.OFF.getType();
	}

	private static enum LevelType {

		OFF(2147483647),

		FATAL(50000),

		ERROR(40000),

		WARN(30000),

		INFO(20000),

		DEBUG(10000),

		ALL(-2147483648),

		CUSTOMER(20050);

		int type;

		public int getType() {
			return type;
		}

		private LevelType(int type) {
			this.type = type;
		}
	}

}
