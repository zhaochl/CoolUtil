package com.zcl.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpClientUtil implements IHttpClientUtil{

	private CloseableHttpClient httpclient;
	private int postStatus;
	private String postResult;
	private int getStatus;
	private String getResult;
	
	public HttpClientUtil() {
		this.httpclient = HttpClients.createDefault();
	}
	
	@Override
	public void get(String url) {
		CloseableHttpResponse response = null;

		try {
			HttpGet httpget = new HttpGet(url);
			response = httpclient.execute(httpget);
//			result = EntityUtils.toString(response.getEntity());
			getResult = EntityUtils.toString(response.getEntity());
			postStatus = response.getStatusLine().getStatusCode();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String get(String url, JSONObject jo) {
		CloseableHttpResponse response = null;
		String result = "";
		String queryUrl = "";
		Iterator it = jo.keys();
		if (jo.length() != 0) {
			queryUrl = url + "?";
			while (it.hasNext()) {
				String key = (String) it.next();
				// System.out.println("key:"+key);
				String val = jo.get(key).toString();
				queryUrl += key + "=" + val + "&";

			}
			queryUrl = queryUrl.substring(0, queryUrl.lastIndexOf("&"));
		} else {
			queryUrl = url;
		}
		try {
			HttpGet httpget = new HttpGet(queryUrl);
			response = httpclient.execute(httpget);
//			result = EntityUtils.toString(response.getEntity());
			getResult = EntityUtils.toString(response.getEntity());
			getStatus = response.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public void post(String url, String json) {
		try {
			HttpPost postRequest = new HttpPost(url);
			StringEntity input = new StringEntity(json);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpclient.execute(postRequest);
			postResult = EntityUtils.toString(response.getEntity());
			postStatus = response.getStatusLine().getStatusCode();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void post(String url, JSONObject jo) {
		int result = 0;
		try {
			HttpPost postRequest = new HttpPost(url);
			List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
			Iterator joIt = jo.keys();
			while (joIt.hasNext()) {
				String key = joIt.next().toString();
				String value = jo.getString(key);
				BasicNameValuePair bnvp = new BasicNameValuePair(key, value);
				pairList.add(bnvp);
			}
			postRequest.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
			HttpResponse response = httpclient.execute(postRequest);
			postResult = EntityUtils.toString(response.getEntity());
			postStatus = response.getStatusLine().getStatusCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return result;
	}

	@Override
	public void shutdown() {

		httpclient.getConnectionManager().shutdown();
	}

	@Override
	public CloseableHttpClient getHttpclient() {
		return httpclient;
	}

	public void setHttpclient(CloseableHttpClient httpclient) {
		this.httpclient = httpclient;
	}

	@Override
	public int getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(int postStatus) {
		this.postStatus = postStatus;
	}

	@Override
	public String getPostResult() {
		return postResult;
	}

	public void setPostResult(String postResult) {
		this.postResult = postResult;
	}

	@Override
	public int getGetStatus() {
		return getStatus;
	}

	public void setGetStatus(int getStatus) {
		this.getStatus = getStatus;
	}

	@Override
	public String getGetResult() {
		return getResult;
	}

	public void setGetResult(String getResult) {
		this.getResult = getResult;
	}

	public static void main(String[] args) {

		String result = "";
		String url = "";
		JSONObject jo;
		HttpClientUtil hc = new HttpClientUtil();
		System.out.println("===========1=================");
		url = "http://localhost:9200/idx_shop_client_info/_search?pretty=true&q=status:1";
		
		hc.get(url);
		result = hc.getGetResult();
		System.out.println(result);
		System.out.println("===========2=================");
		url = "http://localhost:9200/idx-new-order2/_search";
		jo = new JSONObject();
		jo.put("q", "status:1");
		jo.put("pretty", "true");
		result = hc.get(url, jo);
		System.out.println(result);
		System.out.println("===========3=================");
		url = "http://localhost:9200/im-2015-06-27/_search?pretty";
		String json = "{\"from\":1,\"size\":10,\"fields\":[\"@timestamp\",\"msg.Message_from\",\"msg.Message_to\",\"msg.Message_content\"],\"query\":{\"term\":{\"level\":\"INFO\"}},\"filter\":{\"range\":{\"@timestamp\":{\"from\":1435334701142,\"to\":1435378201000}}}}";
		
		hc.post(url, json);
		int iresult = hc.getPostStatus();
		System.out.println(iresult);
		System.out.println(hc.getPostResult());
		System.out.println("===========4=================");
		url = "http://localhost:8888/SpringMVC4/user/add.do";
		jo.put("username", "http1");
		jo.put("password", "http2");
		
		hc.post(url, jo);
		iresult = hc.getPostStatus();
		System.out.println(iresult);
		System.out.println(hc.getPostResult());
	}
}
