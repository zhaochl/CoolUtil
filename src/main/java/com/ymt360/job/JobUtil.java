package com.ymt360.job;

import java.util.Vector;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class JobUtil implements IJobUtil{


	private Vector<JobVo> jobsV;
	public JobUtil(){
		jobsV = new Vector<JobVo>();
	}
	@Override
	public void addJob(JobVo job) {
		// TODO Auto-generated method stub
		jobsV.add(job);
	}

	@Override
	public void cancelJob(String jobName) {
		// TODO Auto-generated method stub
		for(int i=0;i<jobsV.size();i++){
			JobVo jv = jobsV.get(i);
			if(jv.getJobName().equals(jobName)){
				jobsV.remove(i);
				break;
			}
		}
	}

	@Override
	public void reloadAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reloadJob(int jobID) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run(){
		for(int i=0;i<jobsV.size();i++){
			try {
				jobsV.get(i).execute(null);
			} catch (JobExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void runAll() throws SchedulerException{
		for(int i=0;i<jobsV.size();i++){
			
			JobVo jv = jobsV.get(i);
			System.out.println("jobName:"+jv.getJobName()+" is runing.ID:"+jv.getID());
			JobDetail jobDetail= JobBuilder.newJob(jv.getClass())
//	                .withIdentity("testJob_1","group_1")
					.withIdentity(jv.getID(),"group_1")
	                .build();
	        jobDetail.getJobDataMap().put("color", "Green");
	        Trigger trigger = null;
	        if(jv.getRepeatMethod().equals("s")){
	        	trigger= TriggerBuilder
	                .newTrigger()
//	                .withIdentity("trigger_1","group_1")
	                .withIdentity(jv.getID()+"_trigger","group_1")
	                .startNow()
	                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
	                        .withIntervalInSeconds(jv.getRepeatTime())
	                        //时间间隔
//	                        .withRepeatCount(5)        //重复次数(将执行6次)
//	                        .withRepeatCount(jv.getRepeatTime())
	                        .repeatForever()
	                        )
	                .build();
	        }
	        SchedulerFactory sf = new StdSchedulerFactory();
	        Scheduler sched = sf.getScheduler();
	 
	        sched.scheduleJob(jobDetail,trigger);
	 
	        sched.start();
		}
	}
}
