package com.doppio.workplace.scheduler.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author DADA
 * @Description :BatchComm Vo
 * @Class : BatchCommVo
 * 
 * <pre>
 * << 개정이력(Modification Information) >> 
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2024. 05. 01.   DADA     최조생성 
 * </pre>
 * @version : 1.0
 */
public class BatchCommVo  implements Serializable {

	
	
	
	private static final long serialVersionUID = -8061637100207203448L;
	
	/*어플리케이션코드(프로그램 아이디)*/
	private String applCd;
	/*실행구분코드(B:배치, M:수동실행, R:실시간)*/
	private String ifCd;
	
	
	
	/*등록자아이디*/
	private String regId;
	/*등록자아이디*/
	private String regDt;
	/*실행사용자아이디(시스템 또는 사용자고유번호)*/
	private String workId;
	
	/*Param data*/
	private String paramData01;
	
	/*Param data*/
	private String paramData02;
	
	/*실행 구분 */
	private String dtlCd;
	
	/**1.응답코드*/
	private String spRspnsCd  ;
	/**2.MESSAGE*/
	private String spMessage  ;
	
	/*Param List Map*/
	private List<HashMap<String, String>> paramListMap;	
	
	
	
	
	public String getDtlCd() {
		return dtlCd;
	}
	public void setDtlCd(String dtlCd) {
		this.dtlCd = dtlCd;
	}
	public String getParamData02() {
		return paramData02;
	}
	public void setParamData02(String paramData02) {
		this.paramData02 = paramData02;
	}
	public String getApplCd() {
		return applCd;
	}
	public void setApplCd(String applCd) {
		this.applCd = applCd;
	}
	public String getIfCd() {
		return ifCd;
	}
	public void setIfCd(String ifCd) {
		this.ifCd = ifCd;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getParamData01() {
		return paramData01;
	}
	public void setParamData01(String paramData01) {
		this.paramData01 = paramData01;
	}
	public String getSpRspnsCd() {
		return spRspnsCd;
	}
	public void setSpRspnsCd(String spRspnsCd) {
		this.spRspnsCd = spRspnsCd;
	}
	public String getSpMessage() {
		return spMessage;
	}
	public void setSpMessage(String spMessage) {
		this.spMessage = spMessage;
	}
	public List<HashMap<String, String>> getParamListMap() {
		return paramListMap;
	}
	public void setParamListMap(List<HashMap<String, String>> paramListMap) {
		this.paramListMap = paramListMap;
	}
	
	
	
	
	
	
	
	
}
