package com.doppio.workplace.scheduler.service;

import java.io.Serializable;

/**
 * @author DADA
 * @Description :Interface Vo
 * @Class : InterfaceVo
 * 
 * <pre>
 * << 개정이력(Modification Information) >> 
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2022. 08. 30.   DADA     최조생성 
 * </pre>
 * @version : 1.0
 */
public class InterfaceVo  implements Serializable {

	
	private static final long serialVersionUID = -8061637100107203440L;
	
	/*어플리케이션코드(프로그램 아이디)*/
	private String applCd;
	/*실행구분코드(B:배치, M:수동실행, R:실시간)*/
	private String ifCd;
	/*시작일시*/
	private String startDt;
	/*시작일시*/
	private String endDt;
	/*어플리케이션명칭*/
	private String applNm;
	/*어플리케이션명칭*/
	private String applSubNm;
	/*성공여부*/
	private String succYn;
	/*성공여부*/
	private String rsltCd;
	/*결과메시지*/
	private String rsltMsg;
	/*결과건수*/
	private String recCnt;
	/*생성일자*/
	private String genDy;
	/*등록자아이디*/
	private String regId;
	/*등록자아이디*/
	private String regDt;
	/*실행사용자아이디(시스템 또는 사용자고유번호)*/
	private String workId;
	
	
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
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getApplNm() {
		return applNm;
	}
	public void setApplNm(String applNm) {
		this.applNm = applNm;
	}
	public String getApplSubNm() {
		return applSubNm;
	}
	public void setApplSubNm(String applSubNm) {
		this.applSubNm = applSubNm;
	}
	public String getSuccYn() {
		return succYn;
	}
	public void setSuccYn(String succYn) {
		this.succYn = succYn;
	}
	public String getRsltCd() {
		return rsltCd;
	}
	public void setRsltCd(String rsltCd) {
		this.rsltCd = rsltCd;
	}
	
	public String getRsltMsg() {
		return rsltMsg;
	}
	public void setRsltMsg(String rsltMsg) {
		this.rsltMsg = rsltMsg;
	}
	public String getRecCnt() {
		return recCnt;
	}
	public void setRecCnt(String recCnt) {
		this.recCnt = recCnt;
	}
	public String getGenDy() {
		return genDy;
	}
	public void setGenDy(String genDy) {
		this.genDy = genDy;
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
	
	
}
