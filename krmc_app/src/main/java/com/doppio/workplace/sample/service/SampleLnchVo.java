package com.doppio.workplace.sample.service;

import java.io.Serializable;

import com.doppio.workplace.sample.common.service.SmpCommonSearchVo;

public class SampleLnchVo extends SmpCommonSearchVo implements Serializable  {

	private static final long serialVersionUID = -2968116458030286191L;
	
	/******검색조건******/
	/**상품카테고리*/
	private String srchCatCd;
	
	/**진행상태*/
	private String srchLnchStat;
	
	/**제안기간 시작일*/
	private String srchPrpsStDy;
	
	/**제안기간 종료일*/
	private String srchPrpsEdDy;
	
	/**대표상품 명*/
	private String srchRprsnPrdctNm;
	
	/**업체명*/
	private String srchCompNm;
	/******검색조건******/
	
	/**대분류명*/
	private String largeCatCdNm;
	
	/**대분류 코드*/
	private String largeCatCd;
	
	/**중분류명*/
	private String midCatCdNm;

	/**중분류 코드*/
	private String midCatCd;
	
	/**접수번호*/
	private String acceptNo;
	
	/**대표상품명*/
	private String rprsnPrdctNm;
	
	/**업체코드*/
	private String compCd;
	
	/**업체명*/
	private String compNm;
	
	/**담당MD명*/
	private String utakmnMdNm;
	
	/**담당MD 고유번호*/
	private String utakmnMdCd;
	
	/**입점진행상태명*/
	private String lnchStatCdNm;
	
	/**입점진행상태코드*/
	private String lnchStatCd;
	
	/**제안일자*/
	private String prpsDtFmt;
	
	/**완료일자*/
	private String complDtFmt;
	
	/**상선위대상여부*/
	private String prdSlcCmtYn;

	public String getSrchCatCd() {
		return srchCatCd;
	}

	public void setSrchCatCd(String srchCatCd) {
		this.srchCatCd = srchCatCd;
	}

	public String getSrchLnchStat() {
		return srchLnchStat;
	}

	public void setSrchLnchStat(String srchLnchStat) {
		this.srchLnchStat = srchLnchStat;
	}

	public String getSrchPrpsStDy() {
		return srchPrpsStDy;
	}

	public void setSrchPrpsStDy(String srchPrpsStDy) {
		this.srchPrpsStDy = srchPrpsStDy;
	}

	public String getSrchPrpsEdDy() {
		return srchPrpsEdDy;
	}

	public void setSrchPrpsEdDy(String srchPrpsEdDy) {
		this.srchPrpsEdDy = srchPrpsEdDy;
	}

	public String getSrchRprsnPrdctNm() {
		return srchRprsnPrdctNm;
	}

	public void setSrchRprsnPrdctNm(String srchRprsnPrdctNm) {
		this.srchRprsnPrdctNm = srchRprsnPrdctNm;
	}

	public String getSrchCompNm() {
		return srchCompNm;
	}

	public void setSrchCompNm(String srchCompNm) {
		this.srchCompNm = srchCompNm;
	}

	public String getLargeCatCdNm() {
		return largeCatCdNm;
	}

	public void setLargeCatCdNm(String largeCatCdNm) {
		this.largeCatCdNm = largeCatCdNm;
	}

	public String getLargeCatCd() {
		return largeCatCd;
	}

	public void setLargeCatCd(String largeCatCd) {
		this.largeCatCd = largeCatCd;
	}

	public String getMidCatCdNm() {
		return midCatCdNm;
	}

	public void setMidCatCdNm(String midCatCdNm) {
		this.midCatCdNm = midCatCdNm;
	}

	public String getMidCatCd() {
		return midCatCd;
	}

	public void setMidCatCd(String midCatCd) {
		this.midCatCd = midCatCd;
	}

	public String getAcceptNo() {
		return acceptNo;
	}

	public void setAcceptNo(String acceptNo) {
		this.acceptNo = acceptNo;
	}

	public String getRprsnPrdctNm() {
		return rprsnPrdctNm;
	}

	public void setRprsnPrdctNm(String rprsnPrdctNm) {
		this.rprsnPrdctNm = rprsnPrdctNm;
	}

	public String getCompCd() {
		return compCd;
	}

	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}

	public String getCompNm() {
		return compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getUtakmnMdNm() {
		return utakmnMdNm;
	}

	public void setUtakmnMdNm(String utakmnMdNm) {
		this.utakmnMdNm = utakmnMdNm;
	}

	public String getUtakmnMdCd() {
		return utakmnMdCd;
	}

	public void setUtakmnMdCd(String utakmnMdCd) {
		this.utakmnMdCd = utakmnMdCd;
	}

	public String getLnchStatCdNm() {
		return lnchStatCdNm;
	}

	public void setLnchStatCdNm(String lnchStatCdNm) {
		this.lnchStatCdNm = lnchStatCdNm;
	}

	public String getLnchStatCd() {
		return lnchStatCd;
	}

	public void setLnchStatCd(String lnchStatCd) {
		this.lnchStatCd = lnchStatCd;
	}

	public String getPrpsDtFmt() {
		return prpsDtFmt;
	}

	public void setPrpsDtFmt(String prpsDtFmt) {
		this.prpsDtFmt = prpsDtFmt;
	}

	public String getComplDtFmt() {
		return complDtFmt;
	}

	public void setComplDtFmt(String complDtFmt) {
		this.complDtFmt = complDtFmt;
	}

	public String getPrdSlcCmtYn() {
		return prdSlcCmtYn;
	}

	public void setPrdSlcCmtYn(String prdSlcCmtYn) {
		this.prdSlcCmtYn = prdSlcCmtYn;
	}
	
	
	
}