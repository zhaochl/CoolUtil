package com.ymt360.db;

import java.util.HashMap;

public class Tb {

	private String tbName;
	private String shortName;
	private HashMap <String,String>fields;
	
	public String getTbName() {
		return tbName;
	}

	public void setTbName(String tbName) {
		this.tbName = tbName;
		this.shortName = tbName.substring(0, 3);
	}


	public HashMap<String, String> getFields() {
		return fields;
	}

	public void setFields(HashMap<String, String> fields) {
		this.fields = (HashMap<String, String>) fields.clone();
	}

	public void putFields(String field,String type) {
		if(this.fields==null){
			this.fields = new HashMap<String,String>();
		}
		this.fields.put(field, type);
	}
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
