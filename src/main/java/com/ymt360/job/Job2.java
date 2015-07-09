package com.ymt360.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 extends JobVo {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("job2 is running");
	}
}
