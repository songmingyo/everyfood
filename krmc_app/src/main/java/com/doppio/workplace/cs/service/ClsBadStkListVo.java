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
public class ClsBadStkListVo extends Page  implements Serializable {
	

	private static final long serialVersionUID = -8797484923598645834L;

	/* 매입처명 */
	private String buyNm;
	/* 랙번호 */
	private String lackNo1;
	/* 품목코드*/
	private String prdtCd;
	/* 품목명*/
	private String prdtNm;
	/* 규격*/
	private String prdtStd;
	/* 단위 */
	private String ordUnitNm;
	/* 재고수량 */
	private String stkQty;
	/* 평균출고량 */
	private String salesQtyAvg;
	/* 재고일수 */
	private String stkDay;
	/* 매입단가 */
	private String buyCost;
	/* 재고금액 */
	private String stkAmt;
	/* 유통기한 */
	private String exprtDt;
	/* 마지막입고일자 */
	private String buyDtLast;
	/* 마지막출고일자 */
	private String salesDtLast;
	/* 마지막소비기한 */
	private String termValLast;
	/* 마지막매출처 */
	private String salesNmLast;
	
	/* 조회조건 */
	/* 일자 */
	private String searchDt;	
	/* 미출고기간 */
	private String searchNotDlvDay;
	/* 저장형태 */
	private String searchStrgType;
	/* 대분류 */
	private String searchLCd;
	/* 중분류 */
	private String searchMCd;
	
	private String salesPrNm;		//영업사원 이름
	private String salesNm;			//매출처 명
	private String srchSalesPrCd;	//[검색조건] 영업사원
		
	
	public String getSrchSalesPrCd() {
		return srchSalesPrCd;
	}
	public void setSrchSalesPrCd(String srchSalesPrCd) {
		this.srchSalesPrCd = srchSalesPrCd;
	}
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
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
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
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public String getStkQty() {
		return stkQty;
	}
	public void setStkQty(String stkQty) {
		this.stkQty = stkQty;
	}
	public String getSalesQtyAvg() {
		return salesQtyAvg;
	}
	public void setSalesQtyAvg(String salesQtyAvg) {
		this.salesQtyAvg = salesQtyAvg;
	}
	public String getStkDay() {
		return stkDay;
	}
	public void setStkDay(String stkDay) {
		this.stkDay = stkDay;
	}
	public String getBuyCost() {
		return buyCost;
	}
	public void setBuyCost(String buyCost) {
		this.buyCost = buyCost;
	}
	public String getStkAmt() {
		return stkAmt;
	}
	public void setStkAmt(String stkAmt) {
		this.stkAmt = stkAmt;
	}
	public String getExprtDt() {
		return exprtDt;
	}
	public void setExprtDt(String exprtDt) {
		this.exprtDt = exprtDt;
	}
	public String getBuyDtLast() {
		return buyDtLast;
	}
	public void setBuyDtLast(String buyDtLast) {
		this.buyDtLast = buyDtLast;
	}
	public String getSalesDtLast() {
		return salesDtLast;
	}
	public void setSalesDtLast(String salesDtLast) {
		this.salesDtLast = salesDtLast;
	}
	public String getTermValLast() {
		return termValLast;
	}
	public void setTermValLast(String termValLast) {
		this.termValLast = termValLast;
	}
	public String getSalesNmLast() {
		return salesNmLast;
	}
	public void setSalesNmLast(String salesNmLast) {
		this.salesNmLast = salesNmLast;
	}
	public String getSearchDt() {
		return searchDt;
	}
	public void setSearchDt(String searchDt) {
		this.searchDt = searchDt;
	}
	public String getSearchNotDlvDay() {
		return searchNotDlvDay;
	}
	public void setSearchNotDlvDay(String searchNotDlvDay) {
		this.searchNotDlvDay = searchNotDlvDay;
	}
	public String getSearchStrgType() {
		return searchStrgType;
	}
	public void setSearchStrgType(String searchStrgType) {
		this.searchStrgType = searchStrgType;
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
