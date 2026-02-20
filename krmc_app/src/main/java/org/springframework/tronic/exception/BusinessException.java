package org.springframework.tronic.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -1488672723645125500L;
	
	private String errCode;
	private String errMsg;
	
	public BusinessException(String errCode, Throwable cause) {
		super(cause);
		this.errCode = errCode;
	}
	
	public BusinessException(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public BusinessException(String errCode, String errMsg, Throwable cause) {
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