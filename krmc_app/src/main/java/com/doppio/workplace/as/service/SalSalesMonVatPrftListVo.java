package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 매출처 월별 이익현황
 * @Class : SalSalesPrdtPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class SalSalesMonVatPrftListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 매출구분 */
	private String salesClassNm;
	/** 매출처명 */
	private String salesNm;
	/** 전월 잔액 */
	private String prevBalAmt;
	/** 입고금액 */
	private String buyAmt;
	/** 출고금액 */
	private String pureAmt;
	/** 매출이익 */
	private String salesProfit;
	/** 마진율(%) */
	private String marRate;
	/** 입금 */
	private String thisDepAmt;
	/** 잔액 */
	private String thisBalAmt;
	/** 결제조건 */
	private String setlCon;
	/** 영업사원 */
	private String salesPrNm;
	/** 장려비율*/
	private String subsidyRate;
	
	/** Footer Sum */
	private String marRateSum;
	
	/** 조회월 */
	private String searchMn;
	/** 매출처코드 */
	private String searchSalesCd;
	/** 영업사원 */
	private String searchSalesPrCd;
	/** 매출구분 */
	private String searchSalesClass;
	/** 조회구분 */
	private String searchHqClass;
	/** 영업사원 */
	private String salesPrCd;
	
	
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}
	public String getSalesClassNm() {
		return salesClassNm;
	}
	public void setSalesClassNm(String salesClassNm) {
		this.salesClassNm = salesClassNm;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getPrevBalAmt() {
		return prevBalAmt;
	}
	public void setPrevBalAmt(String prevBalAmt) {
		this.prevBalAmt = prevBalAmt;
	}
	public String getBuyAmt() {
		return buyAmt;
	}
	public void setBuyAmt(String buyAmt) {
		this.buyAmt = buyAmt;
	}
	public String getPureAmt() {
		return pureAmt;
	}
	public void setPureAmt(String pureAmt) {
		this.pureAmt = pureAmt;
	}
	public String getSalesProfit() {
		return salesProfit;
	}
	public void setSalesProfit(String salesProfit) {
		this.salesProfit = salesProfit;
	}
	public String getMarRate() {
		return marRate;
	}
	public void setMarRate(String marRate) {
		this.marRate = marRate;
	}
	public String getThisDepAmt() {
		return thisDepAmt;
	}
	public void setThisDepAmt(String thisDepAmt) {
		this.thisDepAmt = thisDepAmt;
	}
	public String getThisBalAmt() {
		return thisBalAmt;
	}
	public void setThisBalAmt(String thisBalAmt) {
		this.thisBalAmt = thisBalAmt;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getSubsidyRate() {
		return subsidyRate;
	}
	public void setSubsidyRate(String subsidyRate) {
		this.subsidyRate = subsidyRate;
	}
	public String getMarRateSum() {
		return marRateSum;
	}
	public void setMarRateSum(String marRateSum) {
		this.marRateSum = marRateSum;
	}
	public String getSearchMn() {
		return searchMn;
	}
	public void setSearchMn(String searchMn) {
		this.searchMn = searchMn;
	}
	public String getSearchSalesCd() {
		return searchSalesCd;
	}
	public void setSearchSalesCd(String searchSalesCd) {
		this.searchSalesCd = searchSalesCd;
	}
	public String getSearchSalesPrCd() {
		return searchSalesPrCd;
	}
	public void setSearchSalesPrCd(String searchSalesPrCd) {
		this.searchSalesPrCd = searchSalesPrCd;
	}
	public String getSearchSalesClass() {
		return searchSalesClass;
	}
	public void setSearchSalesClass(String searchSalesClass) {
		this.searchSalesClass = searchSalesClass;
	}
	public String getSearchHqClass() {
		return searchHqClass;
	}
	public void setSearchHqClass(String searchHqClass) {
		this.searchHqClass = searchHqClass;
	}
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}
	
	
	
	
	
}
