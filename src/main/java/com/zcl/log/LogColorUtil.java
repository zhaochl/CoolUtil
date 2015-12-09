package com.zcl.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * 
 * @author zcl
 * 
 */
public class LogColorUtil {

	public static String runCmd(String cmd) {
		StringBuffer cmdout = new StringBuffer();
		String[] commands = new String[] { "/bin/bash", "-c", cmd };
		// Process process = Runtime.getRuntime().exec(cmd); //执行一个系统命令
		
		try {
			Process process = Runtime.getRuntime().exec(commands);;
			InputStream fis = process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {
				cmdout.append(line);
				cmdout.append("\n");// System.getProperty("line.separator")
			}
			//System.out.println("cmd-result:" + cmdout.toString().trim());
			fis.close();
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cmdout.toString().trim();
	}

	public static void log(String tag,String content) {
		String cmd="";
		if(tag.equals("i")){
			// echo -e "\033[32m 绿色字 \033[0m"
			cmd = "\033[32m" + "info:"+content + " \033[0m";
		}else if(tag.equals("e")){
			// echo -e "\033[31m 红色字 \033[0m"
			cmd = "\033[31m" + "error:"+ content + " \033[0m";
		}else if(tag.equals("r")){
			// echo -e "\033[44;37;5m ME \033[0m COOL"
			// 闪烁-蓝底-白色字
			cmd = "\033[44;37;5m" + "result:"+ content + " \033[0m";
		}
		if(!cmd.equals("")){
			System.out.println(cmd);
//			runCmd(cmd);
		}
	}


}
