package com.doppio.management.service;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;


/**
 * @author :DADA
 * @Description :접근로그  Model VO
 * @Class : SysAccessVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2017.08.21  DADA		
 * </pre>
 * @version : 1.0
 */

public class SysAccessVo  extends Page implements Serializable  {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3886942828217203183L;
	/**접근로그인덱스*/
	private String logIdx	  ;
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
	/**최초접근일시*/
	private String regDt      ;
	/**최종접근일시*/
	private String modDt      ;
	/**접근자아이디*/
	private String regId      ;
	/**접근자명*/
	private String userNm     ;
	/**접근자아이디*/
	private String userId	  ;
	/**접근일자From(yyyy/MM/dd or dd/MM/yyyy)*/
	private String accFromDt  ;
	/**접근일자To(yyyy/MM/dd or dd/MM/yyyy)*/
	private String accToDt    ;
	/**프로그램속성명(실행url,이벤트url code:9011)*/
	private String typeFlageNm;
	/**프로그램속성코드(실행url,이벤트url  code:9011)*/
    private String typeFlageCd;
	/**메뉴아이디*/
	private String menuId     ;
	/**메뉴명*/
	private String title 	  ;
	/**메뉴추가속성명*/
	private String titleSub   ;
	/**수행시간*/
	private String executeTime;
	/**수행시간평균*/
	private String executeTimeAvg;
	/**로그상세여부*/
	private String dtlLogYn;
	/** 시퀀스 */
	private String seq;
	/** 상세커맨드 */
	private String dtlCommand;
	
	private String dayType ;
	private String searchDate;
	/** 검색조건 */
	private String search_useYn;	// 상세로그 있으면 Y 없으면 N
	
	public String getSearch_useYn() {
		return search_useYn;
	}
	public void setSearch_useYn(String search_useYn) {
		this.search_useYn = search_useYn;
	}
	public String getLogIdx() {
		return logIdx;
	}
	public void setLogIdx(String logIdx) {
		this.logIdx = logIdx;
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
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccFromDt() {
		return accFromDt;
	}
	public String getAccFromDtLocale() {
		return DateUtil.convertDateLocale(accFromDt);
	}
	public void setAccFromDt(String accFromDt) {
		this.accFromDt = accFromDt;
	}
	public String getAccToDt() {
		return accToDt;
	}
	public String getAccToDtLocale() {
		return  DateUtil.convertDateLocale(accToDt);
	}
	public void setAccToDt(String accToDt) {
		this.accToDt = accToDt;
	}
	
	public String getTypeFlageNm() {
		return typeFlageNm;
	}
	public void setTypeFlageNm(String typeFlageNm) {
		this.typeFlageNm = typeFlageNm;
	}
	public String getTypeFlageCd() {
		return typeFlageCd;
	}
	public void setTypeFlageCd(String typeFlageCd) {
		this.typeFlageCd = typeFlageCd;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getTitle() {
		return title;
	}
	public String getTitleSub() {
		return titleSub;
	}
	public void setTitleSub(String titleSub) {
		this.titleSub = titleSub;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDayType() {
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	public String getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}
	public String getExecuteTimeAvg() {
		return executeTimeAvg;
	}
	public void setExecuteTimeAvg(String executeTimeAvg) {
		this.executeTimeAvg = executeTimeAvg;
	}
	
	public String getDtlLogYn() {
		return dtlLogYn;
	}
	public void setDtlLogYn(String dtlLogYn) {
		this.dtlLogYn = dtlLogYn;
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
	
	
	public String getTitlelocale(){
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		String rtnVal = this.title;
		
		if(!"ko".equals(language) && !"ko_KR".equals(language)){
			rtnVal =this.titleSub;
		} 
		
		return rtnVal;
	}
	
	

	public String getRegDtFmt(){
		String rtnVal = this.regDt;
		if(!StringUtils.isEmpty(rtnVal)) 
			rtnVal = DateUtil.convertDate(rtnVal,"yyyyMMddHHmmss","");
		return rtnVal;
	}
	
	public String getModDtFmt(){
		String rtnVal = this.modDt;
		if(!StringUtils.isEmpty(rtnVal)) 
			rtnVal = DateUtil.convertDate(rtnVal,"yyyyMMddHHmmss","");
		return rtnVal;
	}
	
	
	
}
