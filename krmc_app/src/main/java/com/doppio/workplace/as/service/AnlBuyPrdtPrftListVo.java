package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매입처품목별이익현황  vo
 * @Class : AnlBuyPrdtPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.08.27 jdsong
 * </pre>
 * @version : 1.0
 */
public class AnlBuyPrdtPrftListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	
	/* 매입처 */
	private String buyNm;
	/* 품목 */
	private String prdtCd;
	/* 품목명 */
	private String prdtNm;
	/* 품목규격 */
	private String prdtStd;
	/* 단가 */
	private String cost;
	/* 매입금액 */
	private String buyAmt;
	/* 출고단가 */
	private String price;
	/* 출고금액 */
	private String pureAmt;
	/* 매출이익 */
	private String salesPrft;
	/* 마진율 */
	private String marRate;
	/* 출고수량 */
	private String salesQty;
	/* 마진율 합계 */
	private String marRateSum;
	
	/* 조회시작일 */
	private String searchStartDt;
	/* 조회종료일 */
	private String searchEndDt;
	/* 매입처명 */
	private String searchBuyCd;
	/* 품목 */
	private String searchPrdtCd;
	/* 대분류 */
	private String searchLCd;
	/* 중분류 */
	private String searchMCd;
	
	
	
	public String getMarRateSum() {
		return marRateSum;
	}
	public void setMarRateSum(String marRateSum) {
		this.marRateSum = marRateSum;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
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
	public String getSalesPrft() {
		return salesPrft;
	}
	public void setSalesPrft(String salesPrft) {
		this.salesPrft = salesPrft;
	}
	public String getMarRate() {
		return marRate;
	}
	public void setMarRate(String marRate) {
		this.marRate = marRate;
	}
	public String getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
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
	public String getSearchBuyCd() {
		return searchBuyCd;
	}
	public void setSearchBuyCd(String searchBuyCd) {
		this.searchBuyCd = searchBuyCd;
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
	

	
}
