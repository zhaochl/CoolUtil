package com.ymt360.sqlite;

public interface ISqliteUtil {

	public void setData(String rkey, String rvalue);
	public String getData(String key);
	public void shutDown();
}
