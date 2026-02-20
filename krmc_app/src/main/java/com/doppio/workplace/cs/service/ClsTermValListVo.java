package com.doppio.workplace.cs.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author song
 * @Description : 소비기한관리  vo
 * @Class : ClsTermValListVo 
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.04.12 song
 * </pre>
 * @version : 1.0
 */
public class ClsTermValListVo extends Page  implements Serializable {
	

	private static final long serialVersionUID = -8797484923598645834L;

	/* 품목명*/
	private String prdtNm;
	/* 규격*/
	private String prdtStd;
	/* 랙번호 */
	private String lackNo1;
	/* 매입처명 */
	private String buyNm;
	/* 단위 */
	private String ordUnitNm;
	/* 입고일자 */
	private String buyDt;
	/* 입고수량 */
	private String buyQty;
	/* 마지막출고일자 */
	private String salesLastDt;
	/* 전월출고수량 */
	private String prevSalesQty;
	/* 재고수량 */
	private String stkQty;
	/* 입고단가 */
	private String buyCost;
	/* 입고금액 */
	private String pureAmt;
	/* 소비기한 */
	private String termVal;
	/* 유통기한 */
	private String exprtDt;
	/* 매출처리스트 */
	private String salesCdList;
	
	/* 조회조건 */
	/* 품목코드 */
	private String searchPrdtCd;	
	/* 시작일 */
	private String searchStartDt;
	/* 종료일 */
	private String searchEndDt;
	/* 대분류 */
	private String searchLCd;
	/* 중분류 */
	private String searchMCd;
	/* 창고코드 */
	private String searchWhCd;
	
	private String srchSalesPrCd;	// [검색조건] 영업사원 코드
	private String salesPrNm;		// 영업사원 이름
	private String salesNm;			// 매출처 명
	
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getSrchSalesPrCd() {
		return srchSalesPrCd;
	}
	public void setSrchSalesPrCd(String srchSalesPrCd) {
		this.srchSalesPrCd = srchSalesPrCd;
	}
	public String getBuyCost() {
		return buyCost;
	}
	public void setBuyCost(String buyCost) {
		this.buyCost = buyCost;
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
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public String getBuyDt() {
		return buyDt;
	}
	public void setBuyDt(String buyDt) {
		this.buyDt = buyDt;
	}
	public String getBuyQty() {
		return buyQty;
	}
	public void setBuyQty(String buyQty) {
		this.buyQty = buyQty;
	}
	public String getSalesLastDt() {
		return salesLastDt;
	}
	public void setSalesLastDt(String salesLastDt) {
		this.salesLastDt = salesLastDt;
	}
	public String getPrevSalesQty() {
		return prevSalesQty;
	}
	public void setPrevSalesQty(String prevSalesQty) {
		this.prevSalesQty = prevSalesQty;
	}
	public String getStkQty() {
		return stkQty;
	}
	public void setStkQty(String stkQty) {
		this.stkQty = stkQty;
	}
	public String getPureAmt() {
		return pureAmt;
	}
	public void setPureAmt(String pureAmt) {
		this.pureAmt = pureAmt;
	}
	public String getTermVal() {
		return termVal;
	}
	public void setTermVal(String termVal) {
		this.termVal = termVal;
	}
	public String getExprtDt() {
		return exprtDt;
	}
	public void setExprtDt(String exprtDt) {
		this.exprtDt = exprtDt;
	}
	public String getSalesCdList() {
		return salesCdList;
	}
	public void setSalesCdList(String salesCdList) {
		this.salesCdList = salesCdList;
	}
	public String getSearchPrdtCd() {
		return searchPrdtCd;
	}
	public void setSearchPrdtCd(String searchPrdtCd) {
		this.searchPrdtCd = searchPrdtCd;
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
	public String getSearchWhCd() {
		return searchWhCd;
	}
	public void setSearchWhCd(String searchWhCd) {
		this.searchWhCd = searchWhCd;
	}
	
	
	
	
}
