package com.doppio.workplace.sample.service;

import java.io.Serializable;

import com.doppio.workplace.sample.common.service.SmpCommonSearchVo;

public class SampleExcelVo extends SmpCommonSearchVo implements Serializable  {

	private static final long serialVersionUID = -6871532193811075444L;
	
	private String comCd;
	private String comNm;
	private String dtlCd;
	private String dtlNm;
	private String codeDecs;	
	private String sortNo;

	
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
	public String getCodeDecs() {
		return codeDecs;
	}
	public void setCodeDecs(String codeDecs) {
		this.codeDecs = codeDecs;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

}