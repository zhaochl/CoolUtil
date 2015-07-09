package com.ymt360.job;

import org.quartz.SchedulerException;

public class JobMain {

	public static void main(String[] args) {
	
		JobUtil jb = new JobUtil();
		Job1 j1 = new Job1();
		j1.setJobName("job1");
		j1.setRepeatMethod("s");
		j1.setRepeatTime(1);
		jb.addJob(j1);
		
		Job2 j2 = new Job2();
		j2.setJobName("job2");
		j2.setRepeatMethod("s");
		j2.setRepeatTime(2);
		jb.addJob(j2);
		try {
			jb.runAll();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
