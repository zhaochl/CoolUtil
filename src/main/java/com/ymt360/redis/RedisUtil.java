package com.ymt360.redis;


import redis.clients.jedis.Jedis;

public class RedisUtil implements IRedisUtil{

	private String host;
	private int port;
	private Jedis redisInst;
	
	public RedisUtil(){
	}
	public RedisUtil(String host,int port){
		this.setHost(host);
		this.setPort(port);
	}
	public Jedis getRedisInst() {
		if (redisInst == null) {
			try {
				redisInst = new Jedis(host,port);
			} catch (Exception e) {
			}
		}else if(!redisInst.isConnected()){
			redisInst.connect();
		}
		return redisInst;
	}
	
	@Override
//	public void setRedisData(Jedis redis, String rkey, String rvalue) {
	public synchronized void setRedisData(String rkey, String rvalue) {	
		// TODO Auto-generated method stub
		if (!redisInst.isConnected()) {
			redisInst.connect();
		}
		if (redisInst.get(rkey) != null) {
			redisInst.set(rkey, rvalue);
		} else {
			redisInst.append(rkey, rvalue);
		}
	}

	@Override
	public synchronized String getRedisData(String key) {
		// TODO Auto-generated method stub
		if (!redisInst.isConnected()) {
			redisInst.connect();
		}
		return redisInst.get(key);
	}
	@Override
	public void releaseRedis(){
		if(redisInst!=null){
			this.redisInst.disconnect();
			this.redisInst=null;
		}
		System.out.println("release redisInst.....");
		
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
