package com.doppio.workplace.sample.common.service;

import com.doppio.common.model.PageN;

public class SmpCommonSearchVo extends PageN {

	private static final long serialVersionUID = 8091289847756404372L;
	
	private String srchComCd;
	private String srchDtlCd;

	private String regId;
	private String modId;
	private String workId;
	
	public String getSrchComCd() {
		return srchComCd;
	}
	public void setSrchComCd(String srchComCd) {
		this.srchComCd = srchComCd;
	}
	public String getSrchDtlCd() {
		return srchDtlCd;
	}
	public void setSrchDtlCd(String srchDtlCd) {
		this.srchDtlCd = srchDtlCd;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
}
