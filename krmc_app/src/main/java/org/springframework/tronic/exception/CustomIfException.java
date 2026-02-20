package org.springframework.tronic.exception;

public class CustomIfException extends RuntimeException {

	private static final long serialVersionUID = -1488672723645125501L;
	
	private String errCode;
	private String errMsg;
	
	public CustomIfException (String errCode, Throwable cause) {
		super(cause);
		this.errCode = errCode;
	}
	
	public CustomIfException (String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public CustomIfException (String errCode, String errMsg, Throwable cause) {
		super(errMsg, cause);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
