package com.ymt360.job;

import org.quartz.Job;
import org.quartz.SchedulerException;

public interface IJobUtil {

//	public void addJob();
//	public void cancelJob();
	public void reloadAll();
	public void reloadJob(int jobID);
	void run();
//	void addJob(Job job);
	void addJob(JobVo job);
	void cancelJob(String jobName);
	void runAll() throws SchedulerException;
}
