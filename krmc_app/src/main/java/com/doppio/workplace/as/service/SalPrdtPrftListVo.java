package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 품목별이익현황
 * @Class : SalPrdtPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class SalPrdtPrftListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 품목코드 */
	private String prdtCd;
    /** 품목명 */
	private String prdtNm;
	/** 규격 */
	private String prdtStd;
	/** 단가 */
	private String cost;
	/** 판매수량 */
	private String salesQty;
	/** 입고금액 */
	private String rcvAmt;
	/** 판매가 */
	private String price;
	/** 출고금액 */
	private String pureAmt;
	/** 차액 */
	private String diffAmt;
	/** 이익율(%) */
	private String marRate;
	/** 하남재고 */
	private String hStkQty;
	/** 여주재고 */
	private String yStkQty;
	
	/** Footer Sum */
	private String marRateSum;
	
	/** 조회시작일 */
	private String searchFromDt;
	/** 조회종료일 */
	private String searchToDt;
	/** 매출처코드 */
	private String searchSalesCd;
	/** 매출처 본사/지사구분 */
	private String searchHqClass;
	/** 품목코드 */
	private String searchPrdtCd;
	/** 대분류 */
	private String searchLCd;
	/** 중분류 */
	private String searchMCd;
	/** 작업자 */
	private String workId;
	
	
	
	
	public String getSearchHqClass() {
		return searchHqClass;
	}
	public void setSearchHqClass(String searchHqClass) {
		this.searchHqClass = searchHqClass;
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
	
	public String getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}
	public String getRcvAmt() {
		return rcvAmt;
	}
	public void setRcvAmt(String rcvAmt) {
		this.rcvAmt = rcvAmt;
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
	public String getDiffAmt() {
		return diffAmt;
	}
	public void setDiffAmt(String diffAmt) {
		this.diffAmt = diffAmt;
	}
	public String getMarRate() {
		return marRate;
	}
	public void setMarRate(String marRate) {
		this.marRate = marRate;
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
	public String getSearchFromDt() {
		return searchFromDt;
	}
	public void setSearchFromDt(String searchFromDt) {
		this.searchFromDt = searchFromDt;
	}
	public String getSearchToDt() {
		return searchToDt;
	}
	public void setSearchToDt(String searchToDt) {
		this.searchToDt = searchToDt;
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
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	
	

	
	
}
