package com.doppio.common.model;

import java.io.Serializable;
/**
 * @author DADA
 * @Description : 
 * @Class : Result
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2017.09.03  DADA		실행시간, 상세로그 생성 설정
 * </pre>
 * @version : 1.0
 */
public class AccessLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4501607158409554820L;
	
	/**접근로그인덱스*/
	private String logidx	  ;	
	/**브라우저세션아이디*/
	private String sessionId  ;
	/**접근프로그램URL*/
	private String resPgmUrl  ;
	/**접근URL전체경로*/
	private String resFullUrl ;
	/**접근IP*/
	private String resIp      ;
	/**접근횟수*/
	private String resCnt     ;
	/**등록일시*/
	private String regDt      ;
	/**등록자아이디*/
	private String regId      ;
	/**수정일시*/
	private String modDt	  ;
	/**메뉴아이디*/
	private String menuId     ;
	/**실행시간(ms)*/
	private String executeTime;
	/**상세로그 순번 */
	private String seq;
	/**추가세부정보(parameter정보상세)*/
	private String dtlCommand;
	
	/**1.응답코드*/
	private String spRspnsCd  ;
	/**2.MESSAGE*/
	private String spMessage  ;

	
	public String getLogidx() {
		return logidx;
	}
	public void setLogidx(String logidx) {
		this.logidx = logidx;
	}
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getResPgmUrl() {
		return resPgmUrl;
	}
	public void setResPgmUrl(String resPgmUrl) {
		this.resPgmUrl = resPgmUrl;
	}
	public String getResFullUrl() {
		return resFullUrl;
	}
	public void setResFullUrl(String resFullUrl) {
		this.resFullUrl = resFullUrl;
	}
	public String getResIp() {
		return resIp;
	}
	public void setResIp(String resIp) {
		this.resIp = resIp;
	}
	public String getResCnt() {
		return resCnt;
	}
	public void setResCnt(String resCnt) {
		this.resCnt = resCnt;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getDtlCommand() {
		return dtlCommand;
	}
	public void setDtlCommand(String dtlCommand) {
		this.dtlCommand = dtlCommand;
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
	
	
	
}
