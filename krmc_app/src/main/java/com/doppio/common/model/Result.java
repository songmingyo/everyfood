package com.doppio.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author hdh
 * @Description : 
 * @Class : Result
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * </pre>
 * @version : 1.0
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1339167910451253220L;
	
	public static final String SUCCESS = "success";
	public static final String DUPLICATE = "duple";
	public static final String FAIL = "fail";
	public static final String EXCEPTION = "exception";
	public static final String USECHGFAIL = "usechgfail";
	public static final String BUSEXCEPTION  = "busexception";	
	public static final String IFEXCEPTION = "ifexception";
	public static final String NODATA = "nodata";
	
	public static enum ResultCd{
		SUCCESS, DUPLICATE, FAIL, EXCEPTION, USECHGFAIL, BUSEXCEPTION, IFEXCEPTION, NODATA
	}
	
	public Result() {}
	public Result(String msgCd) {
		this.msgCd = msgCd;
	}
	
	/* 처리결과(success, error, duplicate) */
	private String resultCode;
	
	/* 결과 메시지 */
	private String message;
	
	/* 결과 코드 */
	private String msgCd;
	
	/* 결과값 1 */
	private String rtnValue01;
	
	/* 결과값 2 */
	private String rtnValue02;
	
	/* 결과값 3 */
	private String rtnValue03;	
	
	/* 결과 Map */
	private HashMap<String, Object> resultMap;
	
	/* 결과 ListMap */
	private List<Map<String,Object>> resultListMap;
	
	
	public String getRtnValue03() {
		return rtnValue03;
	}
	public void setRtnValue03(String rtnValue03) {
		this.rtnValue03 = rtnValue03;
	}
	public List<Map<String, Object>> getResultListMap() {
		return resultListMap;
	}
	public void setResultListMap(List<Map<String, Object>> resultListMap) {
		this.resultListMap = resultListMap;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsgCd() {
		return msgCd;
	}
	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}
	public String getRtnValue01() {
		return rtnValue01;
	}
	public void setRtnValue01(String rtnValue01) {
		this.rtnValue01 = rtnValue01;
	}
	public String getRtnValue02() {
		return rtnValue02;
	}
	public void setRtnValue02(String rtnValue02) {
		this.rtnValue02 = rtnValue02;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}