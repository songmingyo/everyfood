package com.doppio.common.model;

public class JsonResult {
	public JsonResult(boolean success,String msg){
		this.success = success;
		this.msg = msg;
	}
	public JsonResult(){
		
	}
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private boolean success;
	private String msg;
}