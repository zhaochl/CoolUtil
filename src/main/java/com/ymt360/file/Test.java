package com.ymt360.file;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Test {

public static void main(String[] args) {
	
		
		//fu.setDirName("logs");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		Date d = new Date();
		//System.out.println(d);
		String f = format.format(d);
		//System.out.println(f);
		String fileName = "log-"+f+".log";
		//fu.setFileName(fileName);
		FileUtil fu = new FileUtil("src/main/logs",fileName);
		fu.write("result1:run success.");
		fu.write("result2:run success.");
		List<String> rList = fu.read();
		for(int i=0;i<rList.size();i++){
			System.out.println("read line "+i+":"+rList.get(i));
		}
		fu.append("append-test");
		
	}
}
