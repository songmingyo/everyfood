package com.doppio.workplace.sample.service;

import java.io.Serializable;

public class SampleVmPdfVo implements Serializable  {

	private static final long serialVersionUID = 2161987560956596108L;
	
	private String comCd;
	private String comNm;
	private String comSubNm;
	private String useYn;
	
	private String dtlCd;
	private String dtlNm;
	private String dtlSubNm;
	private String extent01;
	private String sortNo;
	
	private String srchComCd;
	
	public String getComCd() {
		return comCd;
	}
	public void setComCd(String comCd) {
		this.comCd = comCd;
	}
	public String getComNm() {
		return comNm;
	}
	public void setComNm(String comNm) {
		this.comNm = comNm;
	}
	public String getComSubNm() {
		return comSubNm;
	}
	public void setComSubNm(String comSubNm) {
		this.comSubNm = comSubNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDtlCd() {
		return dtlCd;
	}
	public void setDtlCd(String dtlCd) {
		this.dtlCd = dtlCd;
	}
	public String getDtlNm() {
		return dtlNm;
	}
	public void setDtlNm(String dtlNm) {
		this.dtlNm = dtlNm;
	}
	public String getDtlSubNm() {
		return dtlSubNm;
	}
	public void setDtlSubNm(String dtlSubNm) {
		this.dtlSubNm = dtlSubNm;
	}
	public String getExtent01() {
		return extent01;
	}
	public void setExtent01(String extent01) {
		this.extent01 = extent01;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	public String getSrchComCd() {
		return srchComCd;
	}
	public void setSrchComCd(String srchComCd) {
		this.srchComCd = srchComCd;
	}

	
}