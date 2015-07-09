package com.ymt360.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ymt360.factory.ComFactory;
import com.ymt360.proc.ProcHttpClient;
import com.ymt360.proc.ProcJestClient;
import com.ymt360.proc.ProcMain;
import com.ymt360.util.MonitorUtil;
import com.ymt360.util.ProcUtil;
import com.ymt360.vo.NginxLogVo;



public class StressMain {

	private String threadName;
	private String message;
	private CountDownLatch controller;

	public StressMain(){
		this.threadName = "StressMain";
	}
	public void execute() {
		TimeUtil.start();
		P.debug =false;
		int joblen = 200;
		ExecutorService executor = new ThreadPoolExecutor(joblen, joblen * 2,
				0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(15));
		controller = new CountDownLatch(joblen);
		HttpWorker[] jobs = new HttpWorker[joblen];
		FutureResult[] results = new FutureResult[joblen];
		//ProcMain []procMains = new ProcMain[joblen];
		//ProcMain []procMains = new ProcHttpClient[joblen];
		ProcMain []procMains = new ProcJestClient[joblen];
		ComFactory[]factories = new ComFactory[joblen];
		String url = "http://log.ymt360.com:9200/";
		
		//String source = "{\"user\":\"kimchy\"}";
		String line = "222.68.172.190 - - [18/Sep/2013:06:49:57 +0000] \"GET /images/my.jpg HTTP/1.1\" 200 19939 \"http://www.angularjs.cn/A00n\" \"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36\"";
		NginxLogVo nlv = new NginxLogVo(line);
		String source = new JSONObject(nlv).toString();
		System.out.println(source);
		String index="stress_text";
		String type="simple";
		String opType ="index";
		JSONObject stressJO = new JSONObject(source);
		MidVo mv[] = new MidVo[joblen];
		for(int i=0;i<joblen;i++){
			mv[i] = new MidVo();
			mv[i].setMindex(index);
			mv[i].setMtype(type);
			mv[i].setMjo(stressJO);
		}
		
		for (int i = 0; i < joblen; i++) {
			factories[i] = new ComFactory();
			//System.out.println("----"+i+"--:"+factories[i]);
			//procMains[i] = new ProcHttpClient(factories[i].getHttpclient());
			procMains[i] = new ProcJestClient(factories[i].getEsInst());
			//System.out.println("fuck-procMains[i]:"+procMains[i]);
			jobs[i] = new HttpWorker(i,url,procMains[i],opType,mv[i]);
			//executor.submit(jobs[i]);
			results[i] = new FutureResult(jobs[i]);
			executor.submit(results[i]);
		}
		
		for (int i = 0; i < results.length; i++) {
			try {
				if (!results[i].isCancelled()) {
					controller.countDown();
					String ResultMessage = results[i].get().getMessage();
					int opresult = results[i].get().getOpresult();
					JSONArray ja = results[i].get().getOpdata();
					JSONObject jo =(JSONObject) ja.get(0);
					//int status = jo.getInt("status");
					//String name = jo.getString("name");
					//message = threadName + "-" + i + "-result:"
					//		+ opresult + "Node:"+name+",staus:" + status;
					P.print(message);
					System.out.println("success:"+opresult);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		P.p("--------------system is waiting to release resource---------------");
		for (int i = 0; i < joblen; i++) {
			try {
				controller.await();
				executor.shutdownNow();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			P.p("thread is going to release resource");
			procMains[i]=null;
		}
		TimeUtil.stop();
	}
	public void execute(String eshost,int threadNum,int datalen,String cpucmd,String logfile) {
		TimeUtil.start();
		P.debug =false;
		int joblen = threadNum;
		ExecutorService executor = new ThreadPoolExecutor(joblen, joblen * 2,
				0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(15));
		controller = new CountDownLatch(joblen);
		HttpWorker[] jobs = new HttpWorker[joblen];
		FutureResult[] results = new FutureResult[joblen];
		//ProcMain []procMains = new ProcMain[joblen];
		//ProcMain []procMains = new ProcHttpClient[joblen];
		ProcMain []procMains = new ProcJestClient[joblen];
		ComFactory[]factories = new ComFactory[joblen];
		//String url = "http://log.ymt360.com:9200/";
		String url = eshost;
		//String source = "{\"user\":\"kimchy\"}";
		String line = "222.68.172.190 - - [18/Sep/2013:06:49:57 +0000] \"GET /images/my.jpg HTTP/1.1\" 200 19939 \"http://www.angularjs.cn/A00n\" \"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36\"";
		String[] lines = new String[datalen];
		for(int i=0;i<lines.length;i++){
			lines[i] = line;
		}
		NginxLogVo nlv = new NginxLogVo(line);
		String source = new JSONObject(nlv).toString();
		System.out.println(source);
		String index="stress_text";
		String type="simple";
		String opType ="index";
		JSONObject stressJO = new JSONObject(source);
		
		/*MidVo mv[] = new MidVo[joblen];
		for(int i=0;i<joblen;i++){
			mv[i] = new MidVo();
			mv[i].setMindex(index);
			mv[i].setMtype(type);
			mv[i].setMjo(stressJO);
		}*/
		//----------------------batch-version-----------
		JSONArray mja = new JSONArray();
		for(int i=0;i<lines.length;i++){
			NginxLogVo nlvTmp = new NginxLogVo(lines[i]);
			JSONObject joTmp=new JSONObject(nlvTmp);
			mja.put(joTmp);
		}
		MidVo mv = new MidVo();
		mv.setMindex(index);
		mv.setMtype(type);
		mv.setMja(mja);
		
		//-------------------------
		for (int i = 0; i < joblen; i++) {
			factories[i] = new ComFactory(url);
			//System.out.println("----"+i+"--:"+factories[i]);
			//procMains[i] = new ProcHttpClient(factories[i].getHttpclient());
			procMains[i] = new ProcJestClient(factories[i].getEsInst(url));
			
			jobs[i] = new HttpWorker(i,url,procMains[i],opType,mv);
			//jobs[i] = new HttpWorker(i,url,procMains[i],opType,mv[i]);
			//executor.submit(jobs[i]);
			results[i] = new FutureResult(jobs[i]);
			executor.submit(results[i]);
		}
		
		for (int i = 0; i < results.length; i++) {
			try {
				if (!results[i].isCancelled()) {
					controller.countDown();
					String ResultMessage = results[i].get().getMessage();
					int opresult = results[i].get().getOpresult();
					JSONArray ja = results[i].get().getOpdata();
					JSONObject jo =(JSONObject) ja.get(0);
					//int status = jo.getInt("status");
					//String name = jo.getString("name");
					//message = threadName + "-" + i + "-result:"
					//		+ opresult + "Node:"+name+",staus:" + status;
					
					P.print(message);
					System.out.println("success:"+opresult);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		P.p("--------------system is waiting to release resource---------------");
		for (int i = 0; i < joblen; i++) {
			try {
				controller.await();
				executor.shutdownNow();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			P.p("thread is going to release resource");
			procMains[i]=null;
		}
		
		Long cost = TimeUtil.getCost();
		//public MonitorUtil(int threadID, int threadNum, int datalen, long cost,String logfile)
		MonitorUtil mu = new MonitorUtil(0,joblen, datalen, cost,cpucmd, logfile);
		Thread monitor = new Thread(mu);
		monitor.setDaemon(true);
		monitor.start();
		TimeUtil.stop();
		try {
			Thread.currentThread().sleep(2000);
			String result = new ProcUtil().runCmd("sar -u 2 2|sed -n '5p'","\t");
			System.out.println(result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		new StressMain().execute();
		StressMain sm = new StressMain();
		for(int i=0;i<args.length;i++){
			System.out.println("args["+i+"]="+args[i]);
		}
		if(args.length<3){
			System.err.println("Usage:java -jar xx.jar l27.0.0.1:9200 10(threadNum) 10(datalen)");
		}else{
			String eshost =args[0];
			int threadNum = Integer.parseInt(args[1]);
			int datalen=Integer.parseInt(args[2]);
			//String cpucmd = "ps -aux |grep  stress.jar|sed -n 1p|awk '{print $3\"\t\"$4}'";
			String cpucmd="vmstat|sed -n 3p";
			/*String result;
			try {
				result = new ProcUtil().runCmd(cpucmd, "\t");
				System.out.println("fulk---result:"+result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			//String logfile = args[4];
			String logfile = "./result.csv";
			System.out.println("eshost:"+eshost+",threadNum:"+threadNum+",datalen:"+datalen+","+logfile);
			sm.execute(eshost, threadNum,datalen,cpucmd,logfile);
		}
	}
	public static void main1(String[] args) {
		//String cmd = args[args.length-1];
		//String cmd=" ps -aux |grep  stress.jar|sed -n 1p|awk '{print $3\"\t\"$4}'";
		String cmd="vmstat|sed -n 3p";
		try {
			String result = new ProcUtil().runCmd(cmd, "\t");
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
