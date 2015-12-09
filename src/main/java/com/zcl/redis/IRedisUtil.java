package com.zcl.redis;

import java.util.List;
import java.util.Set;

public interface IRedisUtil {

	void setRedisData(String rkey, String rvalue);

	String getRedisData(String key);

	void releaseRedis();

	List getRedisList(String key);

	void addRedisList(String rkey, String rvalue);

	Set getRedisSet(String key);

	void addRedisSet(String rkey, String rvalue);

}
