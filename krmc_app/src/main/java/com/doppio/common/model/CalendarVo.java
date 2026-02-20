package com.doppio.common.model;

import java.io.Serializable;


/**
 * @author lht
 * @Description : 
 * @Class : CalendarVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2020.05.25		lht		최초등록
 * </pre>
 * @version : 1.0
 */
public class CalendarVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8380581977302205394L;
	
	
	private String title;
	private String start;
	private String end;
	private String url;
	private String color;
	
	
	private String memberCd;
	private String compCd;
	private String masterId;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getMemberCd() {
		return memberCd;
	}
	
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	
	public String getCompCd() {
		return compCd;
	}
	
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
}
