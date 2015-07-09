package com.ymt360.es;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Bulk.Builder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

public class JestClientUtil {

	private JestClient esclient;
	public JestClientUtil(String eshost,int esport){
		String connectionUrl = "http://" + eshost + ":" + esport;
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(
				connectionUrl).multiThreaded(true).connTimeout(1000000)
				.readTimeout(1000000).build());
		setEsclient(factory.getObject());
	}
//	public void indexData(JestClient client, String indexName, String typeName,
	public synchronized void indexData(String indexName, String typeName,	String jsondata) {
		// TODO Auto-generated method stub
		try {

			String defaultDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
			Date today = new Date();
			JSONObject jo = new JSONObject(jsondata);
			// System.out.println("es-point2");
			long ori_time_stamp = 0L;
			if (jo.has("created_time")) {
				long create_time_stamp = jo.getLong("created_time");
				//System.out.println("1:" + create_time_stamp);
				ori_time_stamp = jo.getLong("created_time") * 1000;
				//System.out.println("2:" + ori_time_stamp);
			} else if (jo.has("add_time")) {
				ori_time_stamp = jo.getLong("add_time") * 1000;
			} else {
				ori_time_stamp = System.currentTimeMillis();
			}
//			Long fix_timestamp = ori_time_stamp - 28799715L;
			Long fix_timestamp = ori_time_stamp - 28800*1000L;
			//System.out.println("fix_create_time:"+fix_timestamp);
			Timestamp fix_date = new Timestamp(fix_timestamp);
			//System.out.println("fix_date:" + fix_date);
			// String timeStr = new SimpleDateFormat(defaultDatePattern,new
			// Locale("US")).format(today);
			String timeStr = new SimpleDateFormat(defaultDatePattern,
					Locale.CHINA).format(fix_date);
			//System.out.println("ES-@timestamp:" + timeStr);
			jo.append("@timestamp", timeStr);
			String jsonStr = jo.toString();
			//System.out.println("Json:"+jsonStr);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
					Locale.CHINA);
			String shortDateStamp = format.format(fix_date);
			String indexNameStamp = indexName + "-" + shortDateStamp;
			System.out.println("ES-indexName:" + indexName);
			//System.out.println("jsonStr"+jsonStr);
			// //////////////////////
			//checkFrequencyDoor("es_index_frequency");
			// ////////////////////
			Index index = new Index.Builder(jsonStr).index(indexName)
					.type(typeName).refresh(true).build();
			// if(client.)
			getEsclient().execute(index);
			String message = "ES-is indexing wait..success";
			//log(YmtUtil2.class.getName(), "i", message);
			// /////////////////////
			// client.shutdownClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized int indexDataBatch(String indexName, String typeName,	JSONArray ja) {
		// TODO Auto-generated method stub
		int lastDataID=0;
		try {
			if(ja.length()>0){
				// Bulk 两个参数1:索引名称2:类型名称(用文章(article)做类型名称)
				
				Builder bulkBuilder =new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
	        	Index []indexs =  new Index[ja.length()];
	        	//System.out.println("ES-batch-check---------------1+esclient:"+esclient+",len:"+ja.length());
				for (int j = 0; j < ja.length(); j++) {
					JSONObject jsonItem = (JSONObject) ja.get(j);
					//System.out.println(jsonItem.toString());
					if(j==ja.length()-1){
						lastDataID = jsonItem.getInt("offset_id");
						//System.out.println("--------------------------fuck-lastDataID:"+lastDataID);
					}
					jsonItem = convertTimeStamp(jsonItem);
					indexs[j] = new Index.Builder(jsonItem.toString()).index(indexName).type(typeName).build();
	        		bulkBuilder.addAction(indexs[j]).build();
				}
				JestResult result = getEsclient().execute(bulkBuilder.build());
				if(result.isSucceeded()){
					String message = "ES-is indexing in batch_mode,index:"+indexName+",type:"+typeName+" wait..success";
					System.out.println(message);
	        	}
				//System.out.println("ES-check---------------2");
//				log(YmtUtil2.class.getName(), "i", message);
			}//if
			// /////////////////////
			// client.shutdownClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastDataID;
	}
	private JSONObject convertTimeStamp(JSONObject jo) {
		String defaultDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		Date today = new Date();
		//JSONObject jo = new JSONObject(jsondata);
		// System.out.println("es-point2");
		long ori_time_stamp = 0L;
		if (jo.has("created_time")) {
			long create_time_stamp = jo.getLong("created_time");
			//System.out.println("1:" + create_time_stamp);
			ori_time_stamp = jo.getLong("created_time") * 1000;
			//System.out.println("2:" + ori_time_stamp);
		} else if (jo.has("add_time")) {
			ori_time_stamp = jo.getLong("add_time") * 1000;
		} else {
			ori_time_stamp = System.currentTimeMillis();
		}
//		Long fix_timestamp = ori_time_stamp - 28799715L;
		Long fix_timestamp = ori_time_stamp - 28800*1000L;
		//System.out.println("fix_create_time:"+fix_timestamp);
		Timestamp fix_date = new Timestamp(fix_timestamp);
		//System.out.println("fix_date:" + fix_date);
		// String timeStr = new SimpleDateFormat(defaultDatePattern,new
		// Locale("US")).format(today);
		String timeStr = new SimpleDateFormat(defaultDatePattern,
				Locale.CHINA).format(fix_date);
		//System.out.println("ES-@timestamp:" + timeStr);
		jo.append("@timestamp", timeStr);
		return jo;
	}
	public JestClient getEsclient() {
		return esclient;
	}
	public void setEsclient(JestClient esclient) {
		this.esclient = esclient;
	}
}
