package com.doppio.management.service;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author dada
 * @Description :코드관리 vo
 * @Class : CodeInfoVO
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 * </pre>
 * @version : 1.0
 */
public class MgrAuthVo  implements Serializable {

	private static final long serialVersionUID = 3636290257085051085L;

    private String menuId      ;
    private String parentId    ;
    private String title       ;
    private String titleSub	   ;
    private String webPage     ;
    private String excTypeFg   ; //DEVICE[W/M/R]
    private String groupId     ;
    private String memberCd	   ;
    private String pgmQry      ;
    private String pgmInt      ;
    private String pgmUpt      ;
    private String pgmDel      ;
    private String pgmPrt      ;
    private String authAllYn   ;
    private String parentNm    ;
    private String parentSubNm ;
    private String rowspan     ;
    
    private String titleMain	;
    private String titleMid		;
    private String titleEnd		;

    private String titleSubMain	;
    private String titleSubMid	;
    private String titleSubEnd	;
    
    
	private String pgmQryOld	;
	private String pgmIntOld    ;
	private String pgmUptOld    ;
	private String pgmDelOld    ;
	private String pgmPrtOld    ;
	private String authAllYnOld ;
	
	private String[] memberCds  ;
	private String state;
	
	private String workId		;

    
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleSub() {
		return titleSub;
	}
	public void setTitleSub(String titleSub) {
		this.titleSub = titleSub;
	}
	public String getWebPage() {
		return webPage;
	}
	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
	public String getExcTypeFg() {
		return excTypeFg;
	}
	public void setExcTypeFg(String excTypeFg) {
		this.excTypeFg = excTypeFg;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	public String getPgmQry() {
		return pgmQry;
	}
	public void setPgmQry(String pgmQry) {
		this.pgmQry = pgmQry;
	}
	public String getPgmInt() {
		return pgmInt;
	}
	public void setPgmInt(String pgmInt) {
		this.pgmInt = pgmInt;
	}
	public String getPgmUpt() {
		return pgmUpt;
	}
	public void setPgmUpt(String pgmUpt) {
		this.pgmUpt = pgmUpt;
	}
	public String getPgmDel() {
		return pgmDel;
	}
	public void setPgmDel(String pgmDel) {
		this.pgmDel = pgmDel;
	}
	public String getPgmPrt() {
		return pgmPrt;
	}
	public void setPgmPrt(String pgmPrt) {
		this.pgmPrt = pgmPrt;
	}
	public String getAuthAllYn() {
		return authAllYn;
	}
	public void setAuthAllYn(String authAllYn) {
		this.authAllYn = authAllYn;
	}
	public String getParentNm() {
		return parentNm;
	}
	public void setParentNm(String parentNm) {
		this.parentNm = parentNm;
	}
	public String getParentSubNm() {
		return parentSubNm;
	}
	public void setParentSubNm(String parentSubNm) {
		this.parentSubNm = parentSubNm;
	}
	public String getRowspan() {
		return rowspan;
	}
	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}
	public String getPgmQryOld() {
		return pgmQryOld;
	}
	public void setPgmQryOld(String pgmQryOld) {
		this.pgmQryOld = pgmQryOld;
	}
	public String getPgmIntOld() {
		return pgmIntOld;
	}
	public void setPgmIntOld(String pgmIntOld) {
		this.pgmIntOld = pgmIntOld;
	}
	public String getPgmUptOld() {
		return pgmUptOld;
	}
	public void setPgmUptOld(String pgmUptOld) {
		this.pgmUptOld = pgmUptOld;
	}
	public String getPgmDelOld() {
		return pgmDelOld;
	}
	public void setPgmDelOld(String pgmDelOld) {
		this.pgmDelOld = pgmDelOld;
	}
	public String getPgmPrtOld() {
		return pgmPrtOld;
	}
	public void setPgmPrtOld(String pgmPrtOld) {
		this.pgmPrtOld = pgmPrtOld;
	}
	public String getAuthAllYnOld() {
		return authAllYnOld;
	}
	public void setAuthAllYnOld(String authAllYnOld) {
		this.authAllYnOld = authAllYnOld;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String[] getMemberCds() {
		return memberCds;
	}
	public void setMemberCds(String[] memberCds) {
		this.memberCds = memberCds;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitleMain() {
		return titleMain;
	}
	public void setTitleMain(String titleMain) {
		this.titleMain = titleMain;
	}
	public String getTitleMid() {
		return titleMid;
	}
	public void setTitleMid(String titleMid) {
		this.titleMid = titleMid;
	}
	public String getTitleEnd() {
		return titleEnd;
	}
	public void setTitleEnd(String titleEnd) {
		this.titleEnd = titleEnd;
	}
	public String getTitleSubMain() {
		return titleSubMain;
	}
	public void setTitleSubMain(String titleSubMain) {
		this.titleSubMain = titleSubMain;
	}
	public String getTitleSubMid() {
		return titleSubMid;
	}
	public void setTitleSubMid(String titleSubMid) {
		this.titleSubMid = titleSubMid;
	}
	public String getTitleSubEnd() {
		return titleSubEnd;
	}
	public void setTitleSubEnd(String titleSubEnd) {
		this.titleSubEnd = titleSubEnd;
	}
	
	public String getParentLocaleNm(){
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		
		if("ko".equals(language) || "ko_KR".equals(language)){
			return this.parentNm;
		} else return this.parentSubNm;
	}
	
	public String getTitleLocaleNm(){
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		
		if("ko".equals(language) || "ko_KR".equals(language)){
			return this.title;
		} else return this.titleSub;
	}
	
	
	
	
	

}
