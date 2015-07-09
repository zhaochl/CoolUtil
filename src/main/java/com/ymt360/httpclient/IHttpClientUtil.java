package com.ymt360.httpclient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;

public interface IHttpClientUtil {

	void get(String url);

	String get(String url, JSONObject jo);

	void post(String url, String json);

	void post(String url, JSONObject jo);

	void shutdown();

	int getPostStatus();

	String getPostResult();

	int getGetStatus();

	String getGetResult();

	CloseableHttpClient getHttpclient();

}
