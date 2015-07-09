package com.ymt360.job;

import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobVo implements Job {
	private String ID;
	private String jobName;
	private String repeatMethod;//d h m s
	private int repeatTime;//
	public JobVo(){
		this.setID(UUID.randomUUID().toString());
	}
	/**
	 * @param iD
	 * @param jobName
	 * @param repeatMethod
	 * @param repeatTime
	 */
	public JobVo(String iD, String jobName, String repeatMethod, int repeatTime) {
		ID = iD;
		this.jobName = jobName;
		this.repeatMethod = repeatMethod;
		this.repeatTime = repeatTime;
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub

	}

	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getRepeatMethod() {
		return repeatMethod;
	}
	public void setRepeatMethod(String repeatMethod) {
		this.repeatMethod = repeatMethod;
	}
	public int getRepeatTime() {
		return repeatTime;
	}
	public void setRepeatTime(int repeatTime) {
		this.repeatTime = repeatTime;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

}
