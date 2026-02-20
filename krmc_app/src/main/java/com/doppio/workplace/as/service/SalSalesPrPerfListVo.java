package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 엽업실적현황
 * @Class : SalSalesPrPerfListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class SalSalesPrPerfListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 영업담당자 */
	private String salesPrNm;
    /** 매출목표 */
	private String goalAmt;
	/** 매출일계 */
	private String thisDayAmt;
	/** 매출월계 */
	private String thisMonAmt;
	/** 달성금액차액 */
	private String thisMonDiff;
	/** 달성률 */
	private String thisMonRate;
	/** 전월실적 */
	private String prevMonAmt;
	/** 전월대비실적율 */
	private String prevMonRate;
	/** 전년동월실적 */
	private String prevYearAmt;
	/** 전년대비실적율 */
	private String prevYearRate;
	
	/** 영업사원 */
	private String salesPrCd;
	/** 부가세유무 */
	private String vatYn;
	/** 조회시작일 */
	private String searchStartDt;
	/** 조회 종료일 */
	private String searchEndDt;
	
	
	
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}
	
	public String getSearchStartDt() {
		return searchStartDt;
	}
	public void setSearchStartDt(String searchStartDt) {
		this.searchStartDt = searchStartDt;
	}
	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getGoalAmt() {
		return goalAmt;
	}
	public void setGoalAmt(String goalAmt) {
		this.goalAmt = goalAmt;
	}
	public String getThisDayAmt() {
		return thisDayAmt;
	}
	public void setThisDayAmt(String thisDayAmt) {
		this.thisDayAmt = thisDayAmt;
	}
	public String getThisMonAmt() {
		return thisMonAmt;
	}
	public void setThisMonAmt(String thisMonAmt) {
		this.thisMonAmt = thisMonAmt;
	}
	public String getThisMonDiff() {
		return thisMonDiff;
	}
	public void setThisMonDiff(String thisMonDiff) {
		this.thisMonDiff = thisMonDiff;
	}
	public String getThisMonRate() {
		return thisMonRate;
	}
	public void setThisMonRate(String thisMonRate) {
		this.thisMonRate = thisMonRate;
	}
	public String getPrevMonAmt() {
		return prevMonAmt;
	}
	public void setPrevMonAmt(String prevMonAmt) {
		this.prevMonAmt = prevMonAmt;
	}
	public String getPrevMonRate() {
		return prevMonRate;
	}
	public void setPrevMonRate(String prevMonRate) {
		this.prevMonRate = prevMonRate;
	}
	public String getPrevYearAmt() {
		return prevYearAmt;
	}
	public void setPrevYearAmt(String prevYearAmt) {
		this.prevYearAmt = prevYearAmt;
	}
	public String getPrevYearRate() {
		return prevYearRate;
	}
	public void setPrevYearRate(String prevYearRate) {
		this.prevYearRate = prevYearRate;
	}
	
	

}
