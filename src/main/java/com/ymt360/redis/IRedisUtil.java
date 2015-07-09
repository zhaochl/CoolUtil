package com.ymt360.redis;

public interface IRedisUtil {

	void setRedisData(String rkey, String rvalue);

	String getRedisData(String key);

	void releaseRedis();

}
