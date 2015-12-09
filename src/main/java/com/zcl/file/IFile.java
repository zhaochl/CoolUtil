package com.zcl.file;

import java.util.List;
import java.util.Vector;

public interface IFile {

//	public void write(String fileName);
//	public void read(String fileName);
//	public void write(String fileName);
//	String getRunTimePath();
//	String getBinPath();
	String getRootPath();
	String getPath(String dirName, String fileName);
//	void write(String dirName, String fileName);
//	void read(String dirName, String fileName);
	void write(String content);
	void append(String content);
//	void append(String content, boolean random);
	List<String> read();
}
