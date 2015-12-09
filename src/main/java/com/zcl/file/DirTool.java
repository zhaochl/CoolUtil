package com.zcl.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DirTool {

	public static void mkDir(File file) {
		if (file.getParentFile().exists()) {
			file.mkdir();
		} else {
			mkDir(file.getParentFile());
			file.mkdir();
		}
	}

	public static void main(String[] args) {
		System.out.println("file-serpeartor:"+System.getProperty("file.separator"));

		String f = "E:\\Tmp\\10w2\\writeTest3-10w_Copy3.txt";
		System.out.println(f.lastIndexOf(System.getProperty("file.separator")));
		String s = f.substring(f.lastIndexOf(System.getProperty("file.separator"))+1,f.length());
		System.out.println(s);
		
		String filePath = "C:\\2\\q.txt";
		String fileSp = System.getProperty("file.separator");
		int lastDirIndex = filePath.lastIndexOf(fileSp);
		System.out.println(lastDirIndex);
		
		String dirPath = filePath.substring(0, lastDirIndex);
		String fileName = filePath.substring(lastDirIndex + 1);
		System.out.println(dirPath);
		System.out.println(fileName);
		File file = new File(filePath);
		if (!file.exists()) {
			File d = new File(dirPath);
			if (!d.exists()) {
				mkDir(d);
			}
		}
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.append("hi");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
