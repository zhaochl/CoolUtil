package com.ymt360.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcUtil {

    /** 
     * 读取控制命令的输出结果 
     * 
     * @param cmd                命令 
     * @param isPrettify 返回的结果是否进行美化（换行），美化意味着换行，默认不进行美化,当此参数为null时也不美化， 
     * @return 控制命令的输出结果 
     * @throws IOException 
     */ 
    public String runCmd(String cmd, String separator) throws IOException { 
            StringBuffer cmdout = new StringBuffer(); 
            String[] commands = new String[]{"/bin/bash", "-c",cmd};
//            Process process = Runtime.getRuntime().exec(cmd);     //执行一个系统命令 
            Process process = Runtime.getRuntime().exec(commands);
            InputStream fis = process.getInputStream(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(fis)); 
            String line = null; 
            while ((line = br.readLine()) != null) { 
            	cmdout.append(line);
            	cmdout.append(separator);//System.getProperty("line.separator") 
            } 
            System.out.println("cmd-result:"+cmdout.toString().trim());
            fis.close();
            br.close();
            return cmdout.toString().trim(); 
    }
    public static void main(String[] args) {
		try {
			String result = new ProcUtil().runCmd("ls","\t");
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
