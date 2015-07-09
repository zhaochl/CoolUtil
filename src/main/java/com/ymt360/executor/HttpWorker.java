package com.ymt360.executor;

import io.searchbox.client.JestClient;

import java.net.URI;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ymt360.proc.ProcHttpClient;
import com.ymt360.proc.ProcJestClient;
import com.ymt360.proc.ProcMain;


public class HttpWorker implements Callable<ResultVo>{

	private int ID;
	private ProcMain procMain;
	private String url;
	private String threadName;
	private String opType;//curl,index
	private MidVo mv;
	public HttpWorker(int id,String url,ProcMain procMain,String opType,MidVo mv){
		//this.setHttpUtil(procMain);
		this.url = url;
		this.ID =id;
		this.threadName="HttpWork-"+id;
		this.opType =opType;
		this.mv =mv;
		//System.out.println(threadName+"fuck-1:"+procMain);
		if(procMain instanceof ProcHttpClient){
			procMain = (ProcHttpClient)procMain;
		}else if(procMain instanceof ProcJestClient){
			procMain = (ProcJestClient)procMain;
			//System.out.println("fuck-jest");
		}else{
			this.procMain = procMain;
		}
		this.procMain = procMain;
	}


	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public ResultVo call() throws Exception {
		// TODO Auto-generated method stub
		ResultVo rvResult = new ResultVo();
		String procResult="";
		JSONArray ja = new JSONArray();
		if(opType.equals("curl")){
			procMain = (ProcHttpClient)procMain;
			procResult = ((ProcHttpClient) procMain).curlHtmlGet(url);
			//System.out.println(curlResult);
			
			JSONObject jo = new JSONObject(procResult);
			ja.put(jo);
			//System.out.println(ja.toString());
			rvResult.setOpdata(ja);
			rvResult.setOpresult(ja.length());
			rvResult.setMessage("success");
		}else if(opType.equals("index")){
			//System.out.println("index");
			//procMain = (ProcJestClient)procMain;
			//System.out.println(mv.toString());
			//System.out.println(procMain);
//			procResult = ((ProcJestClient) procMain).indexData(mv.getMindex(),mv.getMtype(), mv.getMjo().toString());
			int iprocResult = ((ProcJestClient) procMain).indexDataBatch(mv.getMindex(),mv.getMtype(), mv.getMja());
			procResult = Integer.toString(iprocResult);
			ja.put(mv.getMjo());
			rvResult.setOpdata(ja);
			rvResult.setOpresult(ja.length());
			rvResult.setMessage("success");
			//System.out.println(rvResult.getMessage());
		}
		
		return rvResult;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}

}
