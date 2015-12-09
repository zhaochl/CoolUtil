package com.zcl.util;

public class TimeUtil {

	private static long start;
	private static long end;
	private static long cost;
	public static void start(){
		start = System.currentTimeMillis();
	}
	public static void stop(){
		end = System.currentTimeMillis();
		cost = end-start;
		System.out.println("TimeUtil:cost "+cost+" ms");
	}
	public static Long getCost(){
		end = System.currentTimeMillis();
		if(end > start){
			return end-start;
		}else{
			return 0L;
		}
	}
	public static boolean tryStop(Long timeout){
		end = System.currentTimeMillis();
		cost = end-start;
		return (cost > timeout);
	}
	public static void reset(){
		start = 0;
		end =0;
		cost=0;
	}
	
}
