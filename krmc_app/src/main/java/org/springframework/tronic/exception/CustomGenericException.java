package org.springframework.tronic.exception;

public class CustomGenericException extends RuntimeException  {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1736668485228317959L;
	
	private String errCode;
	private String errMsg;

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

	public CustomGenericException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public CustomGenericException(String errCode, String errMsg, Throwable cause) {
		super(errMsg,cause);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public CustomGenericException(String errCode, Throwable cause) {
		super(cause);
		this.errCode = errCode;
	}
}
