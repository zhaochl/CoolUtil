package com.ymt360.conf;

public class Main {
	/*
	 * server-result zhaochunliang@bi-esdata:~/esmonitor/bin$ ls javaFile.jar
	 * run ymt.conf zhaochunliang@bi-esdata:~/esmonitor/bin$ java -jar
	 * javaFile.jar -f ymt.conf usage:java -jar xx.jar [-f a.conf] user config
	 * file 111 zhaochunliang@bi-esdata:~/esmonitor/bin$ java -jar javaFile.jar
	 * usage:java -jar xx.jar [-f a.conf] rootPath:/home/zhaochunliang/esmonitor
	 * resultPath:/home/zhaochunliang/esmonitor/conf/ymt.conf default config
	 * path: 111
	 */
	public static void main(String[] args) {
		try {
			ConfUtil1 cu = new ConfUtil1();
			if (args.length == 0) {
				cu.setDebugMode(false);
				cu.setDirName("conf");
				cu.setFileName("ymt.conf");
				String redis_host = cu.getConfig("redis_host");
				System.out.println("default config path:");
				System.out.println(redis_host);
			} else {
				if (args[0].equals("-f")) {
					cu.setFullFileNamePath(args[1]);
					String redis_host = cu.getConfig("redis_host");
					System.out.println("user config file");
					System.out.println(redis_host);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("usage:java -jar xx.jar [-f a.conf]");
		} catch (Exception e) {
			System.err.println(e);// TODO: handle exception
		}
	}
}
