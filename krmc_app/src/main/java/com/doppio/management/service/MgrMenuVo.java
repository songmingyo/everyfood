package com.doppio.management.service;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author dada
 * @Description : 메뉴관리 vo
 * @Class : MgrMenuVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 * </pre>
 * @version : 1.0
 */
public class MgrMenuVo  implements Serializable {

	private static final long serialVersionUID = 3636290277045031082L;
	
	/**프로그램 아이디*/
	private String menuId;
	/**프로그램 부모 아이디*/
	private String parentId;
	/**프로그램명칭*/
	private String title;
	/**프로그램확장명칭(영문)*/
	private String titleSub;
	/** 메뉴추가확장제목(베트남어) */
	private String titleExtend;
	/**메뉴타입(Y:빌트인, N:기본메뉴)*/
	private String menuFg;
	/**실행구분(CODE : 9010  0:실행안함, 1:링크, 2:새창, 3:모달창)*/
	private String commandFg;
	/**실행구분명칭*/
	private String commandFgNm;
	/**실행구분서브명칭(영문)*/
	private String commandFgSubNm;
	/**숨김구분(Y/N)*/
	private String hideYn;
	/**사용여부구분(Y/N)*/
	private String useYn;
	/**VDI적용정책코드(9050)*/
	private String vdiCd;
	/**페이지경로*/
	private String webPage;
	/**메뉴경로 확장*/
	private String extraInfo;
	/**메뉴소트레벨*/
	private String sortInLevel;
	/**메뉴레벨*/
	private String menuLevel;
	/**선택된 메뉴분류 구분(L/M/S)*/
	private String nowTypes;
	/**선택된 메뉴의 상위분류*/
	private String parentCommandFg;
	/**상세로그생성여부[Y,N]*/
	private String dtlLogYn;
	/**DEVICE[W/M/R]*/
	private String excTypeFg;
	/**메뉴아이콘*/
	private String menuIcon;
	/**시스템 메뉴 여부(Y/N)*/
	private String buildinYn;
	
	
	/**작업자아이디*/
	private String workId;
	
	private String setLagId;
	private String setMidId;
	private String setSmlId;

	/** 메뉴 대분류 */
	private String sysCode;
	
	
	public String getTitleExtend() {
		return titleExtend;
	}

	public void setTitleExtend(String titleExtend) {
		this.titleExtend = titleExtend;
	}

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

	public String getMenuFg() {
		return menuFg;
	}

	public void setMenuFg(String menuFg) {
		this.menuFg = menuFg;
	}

	public String getCommandFg() {
		return commandFg;
	}

	public void setCommandFg(String commandFg) {
		this.commandFg = commandFg;
	}

	public String getCommandFgNm() {
		return commandFgNm;
	}

	public void setCommandFgNm(String commandFgNm) {
		this.commandFgNm = commandFgNm;
	}

	public String getCommandFgSubNm() {
		return commandFgSubNm;
	}

	public void setCommandFgSubNm(String commandFgSubNm) {
		this.commandFgSubNm = commandFgSubNm;
	}

	public String getHideYn() {
		return hideYn;
	}

	public void setHideYn(String hideYn) {
		this.hideYn = hideYn;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getVdiCd() {
		return vdiCd;
	}

	public void setVdiCd(String vdiCd) {
		this.vdiCd = vdiCd;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getSortInLevel() {
		return sortInLevel;
	}

	public void setSortInLevel(String sortInLevel) {
		this.sortInLevel = sortInLevel;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getNowTypes() {
		return nowTypes;
	}

	public void setNowTypes(String nowTypes) {
		this.nowTypes = nowTypes;
	}

	public String getParentCommandFg() {
		return parentCommandFg;
	}

	public void setParentCommandFg(String parentCommandFg) {
		this.parentCommandFg = parentCommandFg;
	}

	public String getDtlLogYn() {
		return dtlLogYn;
	}

	public void setDtlLogYn(String dtlLogYn) {
		this.dtlLogYn = dtlLogYn;
	}
	
	public String getExcTypeFg() {
		return excTypeFg;
	}

	public void setExcTypeFg(String excTypeFg) {
		this.excTypeFg = excTypeFg;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getSetLagId() {
		return setLagId;
	}

	public void setSetLagId(String setLagId) {
		this.setLagId = setLagId;
	}

	public String getSetMidId() {
		return setMidId;
	}

	public void setSetMidId(String setMidId) {
		this.setMidId = setMidId;
	}

	public String getSetSmlId() {
		return setSmlId;
	}

	public void setSetSmlId(String setSmlId) {
		this.setSmlId = setSmlId;
	}
	
	public String getCommandFgLocaleNm(){
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		
		if("ko".equals(language) || "ko_KR".equals(language)){
			return this.commandFgNm;
		} else return this.commandFgSubNm;
	}
	
	public String getMenuTitleLocaleNm(){
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		
		if("ko".equals(language) || "ko_KR".equals(language)){
			return this.title;
		} else return this.titleSub;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getBuildinYn() {
		return buildinYn;
	}

	public void setBuildinYn(String buildinYn) {
		this.buildinYn = buildinYn;
	}
		
	
}