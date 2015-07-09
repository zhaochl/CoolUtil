package com.ymt360.executor;

import org.json.JSONArray;

public class ResultVo {

	private int opresult;
	private String message;
	private JSONArray opdata;
	/**
	 * @param opresult
	 * @param message
	 */
	public ResultVo(int opresult, String message,JSONArray opdata) {
		this.opresult = opresult;
		this.message = message;
		this.opdata = opdata;
	}
	public ResultVo() {
		// TODO Auto-generated constructor stub
	}
	public int getOpresult() {
		return opresult;
	}
	public void setOpresult(int opresult) {
		this.opresult = opresult;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public JSONArray getOpdata() {
		return opdata;
	}
	public void setOpdata(JSONArray opdata) {
		this.opdata = opdata;
	}
}
