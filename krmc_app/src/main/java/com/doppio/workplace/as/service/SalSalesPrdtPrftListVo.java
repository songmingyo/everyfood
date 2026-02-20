package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 매출처 품목별이익현황
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
public class SalSalesPrdtPrftListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 매출구분 */
	private String salesClass;
	/** 매출처명 */
	private String salesNm;
	/** 품목코드 */
	private String prdtCd;
    /** 품목명 */
	private String prdtNm;
	/** 규격 */
	private String prdtStd;
	/** 입고단가 */
	private String cost;
	/** 입고금액 */
	private String buyAmt;
	/** 출고단가 */
	private String price;
	/** 출고금액 */
	private String pureAmt;
	/** 매출이익 */
	private String salesProfit;
	/** 마진율(%) */
	private String marRate;
	/** 단가기준 */
	private String costClass;
	/** 출고수량 */
	private String salesQty;
	/** 영업사원 */
	private String salesPrNm;
	/** 장려비율 */
	private String subsidyRate;
	
	/** 하남재고 */
	private String hStkQty;
	/** 여주재고 */
	private String yStkQty;
	
	/** Footer Sum */
	private String marRateSum;
	
	/** 조회시작일 */
	private String searchStartDt;
	/** 조회종료일 */
	private String searchEndDt;
	/** 매출처코드 */
	private String searchSalesCd;
	/** 품목코드 */
	private String searchPrdtCd;
	/** 영업사원 */
	private String searchSalesPrCd;
	/** 매출구분 */
	private String searchSalesClass;
	/** 대분류 */
	private String searchLCd;
	/** 중분류 */
	private String searchMCd;
	/** 조회구분 */
	private String searchHqClass;
	/** 영업사원 */
	private String salesPrCd;
	
	
	
	
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}	
	public String getSearchHqClass() {
		return searchHqClass;
	}
	public void setSearchHqClass(String searchHqClass) {
		this.searchHqClass = searchHqClass;
	}
	public String getSalesClass() {
		return salesClass;
	}
	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
	}
	public String getPrdtNm() {
		return prdtNm;
	}
	public void setPrdtNm(String prdtNm) {
		this.prdtNm = prdtNm;
	}
	public String getPrdtStd() {
		return prdtStd;
	}
	public void setPrdtStd(String prdtStd) {
		this.prdtStd = prdtStd;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public String getBuyAmt() {
		return buyAmt;
	}
	public void setBuyAmt(String buyAmt) {
		this.buyAmt = buyAmt;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getCostClass() {
		return costClass;
	}
	public void setCostClass(String costClass) {
		this.costClass = costClass;
	}
	
	public String getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
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
	public String gethStkQty() {
		return hStkQty;
	}
	public void sethStkQty(String hStkQty) {
		this.hStkQty = hStkQty;
	}
	public String getyStkQty() {
		return yStkQty;
	}
	public void setyStkQty(String yStkQty) {
		this.yStkQty = yStkQty;
	}
	public String getMarRateSum() {
		return marRateSum;
	}
	public void setMarRateSum(String marRateSum) {
		this.marRateSum = marRateSum;
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
	public String getSearchSalesCd() {
		return searchSalesCd;
	}
	public void setSearchSalesCd(String searchSalesCd) {
		this.searchSalesCd = searchSalesCd;
	}
	public String getSearchPrdtCd() {
		return searchPrdtCd;
	}
	public void setSearchPrdtCd(String searchPrdtCd) {
		this.searchPrdtCd = searchPrdtCd;
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
	public String getSearchLCd() {
		return searchLCd;
	}
	public void setSearchLCd(String searchLCd) {
		this.searchLCd = searchLCd;
	}
	public String getSearchMCd() {
		return searchMCd;
	}
	public void setSearchMCd(String searchMCd) {
		this.searchMCd = searchMCd;
	}
	
	
	
	
}
