package com.ymt360.monitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.ymt360.ssh.ProcUtil;


public class MonitorUtil implements Runnable {

	private int threadID;
	private int threadNum;
	private int datalen;
	private long cost;
	private String cpucmd;
	private String logfile;

	/**
	 * @param threadID
	 * @param threadNum
	 * @param datalen
	 * @param cost
	 * @param logfile
	 */
	public MonitorUtil(int threadID, int threadNum, int datalen, long cost,String cpucmd,
			String logfile) {
		this.threadID = threadID;
		this.threadNum = threadNum;
		this.datalen = datalen;
		this.cost = cost;
		this.cpucmd = cpucmd;
		this.logfile = logfile;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("MonitorUtil is running");
		//while (true) {
			try {
				ProcUtil pu = new ProcUtil();
				//String cpuinfo = pu.runCmd("sar -u 2 2|sed -n '5p'", true);
				String cpuinfo = pu.runCmd(cpucmd,"\t");
				System.out.println("cpuinfo:" + cpuinfo);
				File f = new File(logfile);
				FileOutputStream out = null;
				StringBuffer sb = new StringBuffer();
//				Long cost = TimeUtil.getCost();
				if (!f.exists()) {
					sb.append("threadNum\tdataLen\tcosttime\tcpu%\tmem%\n");
					sb.append("\n"+threadNum + "\t" + datalen + "\t" + cost + " ms"+"\t"+
							cpuinfo);
				} else {
					sb.append("\n"+threadNum + "\t" + datalen + "\t" + cost + " ms"+"\t"+
							cpuinfo);
				}
				try {
					out = new FileOutputStream(f, true);
					out.write(sb.toString().getBytes("utf-8"));
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			try {
//				Thread.currentThread().sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

}
