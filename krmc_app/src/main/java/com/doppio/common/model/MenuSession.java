package com.doppio.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author DADA
 * @Description : 메뉴 권한 정보
 * @Class : MenuSession
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 * </pre>
 * @version : 
 */
public class MenuSession extends PermissionResult implements Serializable{

	private static final long serialVersionUID = 899399106108237376L;
	
	/** 세션명 */
	public static final String MANAGER_MENU_SESSION_KEY = "com.doppio.menu";
	
	/*하위메뉴 title */
	private String childTitle;
	/* 메뉴ID */
	private String menuId;
	/* 부모메뉴ID */
	private String parentId;
	/* 자식메뉴ID */
	private String childId;
	/* 메뉴제목 */
	private String title;
	/* 메뉴추가확장제목(다국어) */
	private String titleSub;
	/* 메뉴추가확장제목(다국어) */
	private String titleExtend;
	/* 링크주소 */
	private String webPage;
	/* 추가설명  */
	private String extraInfo;
	/* 단말까지 메뉴ID 목록 */
	private String sort;
	/* 숨김여부 */
	private String hideYn;
	/* 아이피접근제어정책(9050) */
	private String vdiCd;
	/*접근허용아이피정보 */
	private String vdiIps;
	/* 메뉴깊이 */
	private int level;
	/* 단말여부 */
	private int leaf;
	/*상세로그생성여부[Y,N]*/
	private String dtlLogYn;
	   
	/* TOP MENU TITLE */
	private String topMenuTitle;
	/* TOP MENU TITLE SUB */
	private String topMenuTitleSub;
	/* TOP MENU ID */
	private String topMenuId;
	
	/* LOCALE 적용 TITLE */
	private String titleLocale;
	private String childTitleLocale;
	private String topMenuTitleLocale;
	
	private String menuUrl;
	private String sysCode;
	private String topMenuSort;

	/** map 메뉴 목록 */
	private List<MenuMap> menuList;
	/** 메뉴 아이콘*/
	private String menuIcon;

	/** 메뉴 아이콘*/
	private String useYn;

	
	public String getTitleExtend() {
		return titleExtend;
	}
	public void setTitleExtend(String titleExtend) {
		this.titleExtend = titleExtend;
	}
	public String getChildTitle() {
		return childTitle;
	}
	public void setChildTitle(String childTitle) {
		this.childTitle = childTitle;
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
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
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
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getHideYn() {
		return hideYn;
	}
	public void setHideYn(String hideYn) {
		this.hideYn = hideYn;
	}
	public String getVdiCd() {
		return vdiCd;
	}
	public void setVdiCd(String vdiCd) {
		this.vdiCd = vdiCd;
	}
	public String getVdiIps() {
		return vdiIps;
	}
	public void setVdiIps(String vdiIps) {
		this.vdiIps = vdiIps;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLeaf() {
		return leaf;
	}
	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}
	public String getDtlLogYn() {
		return dtlLogYn;
	}
	public void setDtlLogYn(String dtlLogYn) {
		this.dtlLogYn = dtlLogYn;
	}
	public String getTopMenuTitle() {
		return topMenuTitle;
	}
	public void setTopMenuTitle(String topMenuTitle) {
		this.topMenuTitle = topMenuTitle;
	}
	public String getTopMenuTitleSub() {
		return topMenuTitleSub;
	}
	public void setTopMenuTitleSub(String topMenuTitleSub) {
		this.topMenuTitleSub = topMenuTitleSub;
	}
	public String getTopMenuId() {
		return topMenuId;
	}
	public void setTopMenuId(String topMenuId) {
		this.topMenuId = topMenuId;
	}
	public List<MenuMap> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuMap> menuList) {
		this.menuList = menuList;
	}
	public String getTitleLocale() {
		return titleLocale;
	}
	public void setTitleLocale(String titleLocale) {
		this.titleLocale = titleLocale;
	}
	public String getChildTitleLocale() {
		return childTitleLocale;
	}
	public void setChildTitleLocale(String childTitleLocale) {
		this.childTitleLocale = childTitleLocale;
	}
	public String getTopMenuTitleLocale() {
		return topMenuTitleLocale;
	}
	public void setTopMenuTitleLocale(String topMenuTitleLocale) {
		this.topMenuTitleLocale = topMenuTitleLocale;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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
	public String getTopMenuSort() {
		return topMenuSort;
	}
	public void setTopMenuSort(String topMenuSort) {
		this.topMenuSort = topMenuSort;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}