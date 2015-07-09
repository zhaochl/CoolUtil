package com.ymt360.conf;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author zcl
 *
 */

public class ConfUtil{

	private String dirName;
	private String fileName;
	private String fullFileNamePath;
	private boolean debugMode;
	private String separator = System.getProperty("file.separator");
//	private File fileInst;
	public ConfUtil(){}
	/**
	 * @param dirName
	 * @param fileName
	 * @param fileInst
	 */
	public ConfUtil(String dirName, String fileName) {
		this.dirName = dirName;
		this.fileName = fileName;
		fullFileNamePath = getPath(dirName, fileName);
//		File fileInst = new File(fullFileNamePath);
//		this.fileInst = fileInst;
	}
	public String getConfig(String key){
		return getProperties().getProperty(key);
	}
	public Properties getProperties(){
		
		Properties confSetting = new Properties();
		FileInputStream in =null;
		if(dirName!=null && fileName!=null){
			fullFileNamePath = getPath(dirName, fileName);
		}else{
			fullFileNamePath = getFullFileNamePath();
		}
		try {
			in = new FileInputStream(fullFileNamePath);
			confSetting.load(in);
			
			//System.out.println("conf:"+confSetting.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return confSetting;
	}
	
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
	public String getRootPath() {
		// TODO Auto-generated method stub
		String realpath = ConfUtil.class.getProtectionDomain().getCodeSource()
				.getLocation().getFile();
		File jarFile = new File(realpath);
		// System.out.println("parent:"+jarFile.getParent().toString());
		String jarPath = jarFile.getParent().toString();
		File jarDirFile = new File(jarPath);
		String rootPath = jarDirFile.getParent().toString();
		//System.out.println("rootPath:"+rootPath);
		return debugMode?"src"+separator+"main"+separator+"resources":rootPath;
	}

	// /conf ymt.conf
	/**
	 * 
	 * @param dirName conf
	 * @param fileName ymt.conf
	 * @return
	 */
	public String getPath(String dirName,String fileName){
		String rootPath = getRootPath();
		
		String resultPath = rootPath + separator + dirName + separator + fileName;
		//System.out.println("resultPath:"+resultPath);
		return resultPath;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		fullFileNamePath = getPath(dirName, fileName);
		String rootPath = getRootPath();
		String dirPath = rootPath + separator + dirName;
		File dirFile = new File(dirPath);
		//System.out.println("dirPath:"+dirPath);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		this.dirName = dirName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		fullFileNamePath = getPath(dirName, fileName);
		this.fileName = fileName;
	}

	public String getFullFileNamePath() {
		return fullFileNamePath;
	}
	public void setFullFileNamePath(String fullFileNamePath) {
		this.fullFileNamePath = fullFileNamePath;
	}
	public boolean isDebugMode() {
		return debugMode;
	}
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	@Override
	public String toString() {
		return "FileUtil [dirName=" + dirName + ", fileName=" + fileName
				 + "]";
	}
}
