package com.doppio.common.model;

import com.doppio.common.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody {

	
	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	
	@JsonView(Views.Public.class)
	String msgDetail;
	
	@JsonView(Views.Public.class)
	String codeExt;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsgDetail() {
		return msgDetail;
	}

	public void setMsgDetail(String msgDetail) {
		this.msgDetail = msgDetail;
	}

	public String getCodeExt() {
		return codeExt;
	}

	public void setCodeExt(String codeExt) {
		this.codeExt = codeExt;
	}

	@Override
	public String toString() {
		return "AjaxResponseResult [msg=" + msg + ", code=" + code +" | msgDetail="+msgDetail+", codeExt="+codeExt+"]";
	}
}
