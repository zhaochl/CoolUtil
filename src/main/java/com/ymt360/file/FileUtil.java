package com.ymt360.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class FileUtil implements IFile{

	private String dirName;
	private String fileName;
	private String fullFileNamePath;
	private File fileInst;
	public FileUtil(){}
	/**
	 * @param dirName
	 * @param fileName
	 * @param fileInst
	 */
	public FileUtil(String dirName, String fileName) {
		this.dirName = dirName;
		this.fileName = fileName;
		fullFileNamePath = getPath(dirName, fileName);
		File fileInst = new File(fullFileNamePath);
		this.fileInst = fileInst;
	}
	@Override
	public void write(String content) {
		// TODO Auto-generated method stub
		try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fullFileNamePath, false);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<String> read() {
		// TODO Auto-generated method stub
		File file = new File(fullFileNamePath);
        BufferedReader reader = null;
        List<String> rV = new ArrayList<String>();
        try {
           // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                rV.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return rV;
	}

	@Override
	public void append(String content){
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fullFileNamePath, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	//returns / /bin /conf
	@Override
	public String getRootPath() {
		// TODO Auto-generated method stub
		String realpath = FileUtil.class.getProtectionDomain().getCodeSource()
				.getLocation().getFile();
		File jarFile = new File(realpath);
		// System.out.println("parent:"+jarFile.getParent().toString());
		String jarPath = jarFile.getParent().toString();
		File jarDirFile = new File(jarPath);
		String rootPath = jarDirFile.getParent().toString();
		return rootPath;
	}

	
	@Override
	public String getPath(String dirName,String fileName){
		String rootPath = getRootPath();
		String separator = System.getProperty("file.separator");
		String resultPath = rootPath + separator + dirName + separator + fileName;
		return resultPath;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFileInst() {
		return fileInst;
	}
	public void setFileInst(File fileInst) {
		this.fileInst = fileInst;
	}
	public String getFullFileNamePath() {
		return fullFileNamePath;
	}
	public void setFullFileNamePath(String fullFileNamePath) {
		this.fullFileNamePath = fullFileNamePath;
	}
	@Override
	public String toString() {
		return "FileUtil [dirName=" + dirName + ", fileName=" + fileName
				+ ", fileInst=" + fileInst + "]";
	}
}
