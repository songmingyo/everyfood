package com.doppio.common.model;

import java.io.Serializable;


/**
 * @author DADA
 * @Description : 접속메뉴 MAP 
 * @Class : MenuMap
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2017.07.29					최초작성
 * </pre>
 * @version : 
 */
public class MenuMap implements Serializable{

	private static final long serialVersionUID = 2396204970298833525L;
	
	/* 메뉴ID */
	private String menuId;
	/* 메뉴제목 */
	private String title;
	/* 메뉴추가확장제목(다국어) */
	private String titleSub;
	/* 링크주소 */
	private String webPage;
	/* 단말여부 */
	private int leaf;
	
	/* LOCALE 적용 title */
	private String titleLocale;
	private String sysCode;
	
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
	public int getLeaf() {
		return leaf;
	}
	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}
	public String getTitleLocale() {
		return titleLocale;
	}
	public void setTitleLocale(String titleLocale) {
		this.titleLocale = titleLocale;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
}
