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
public class SalSalesMonPrftListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 매출구분 */
	private String salesClassNm;
	/** 매출처명 */
	private String salesNm;
	/** 항목 */
	private String classNm;
	/** 1월 */
	private String mon1;
	/** 2월 */
	private String mon2;
	/** 3월 */
	private String mon3;
	/** 4월 */
	private String mon4;
	/** 5월 */
	private String mon5;
	/** 6월 */
	private String mon6;
	/** 7월 */
	private String mon7;
	/** 8월 */
	private String mon8;
	/** 9월 */
	private String mon9;
	/** 10월 */
	private String mon10;
	/** 11월 */
	private String mon11;
	/** 12월 */
	private String mon12;
	/** 합계 */
	private String monTot;
	/** 영업사원 */
	private String salesPrNm;
	
	
	/** 조회시작일 */
	private String searchYearMnSt;
	/** 조회종료일 */
	private String searchYearMnEt;
	/** 조회구분 */
	private String searchSalesHqClass;
	/** 매출처코드 */
	private String searchSalesCd;
	/** 품목코드 */
	private String searchPrdtCd;
	/** 영업사원 */
	private String searchSalesPrCd;
	/** 매출구분 */
	private String searchSalesClass;
	
	
	
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getMonTot() {
		return monTot;
	}
	public void setMonTot(String monTot) {
		this.monTot = monTot;
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
	public String getClassNm() {
		return classNm;
	}
	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}
	public String getMon1() {
		return mon1;
	}
	public void setMon1(String mon1) {
		this.mon1 = mon1;
	}
	public String getMon2() {
		return mon2;
	}
	public void setMon2(String mon2) {
		this.mon2 = mon2;
	}
	public String getMon3() {
		return mon3;
	}
	public void setMon3(String mon3) {
		this.mon3 = mon3;
	}
	public String getMon4() {
		return mon4;
	}
	public void setMon4(String mon4) {
		this.mon4 = mon4;
	}
	public String getMon5() {
		return mon5;
	}
	public void setMon5(String mon5) {
		this.mon5 = mon5;
	}
	public String getMon6() {
		return mon6;
	}
	public void setMon6(String mon6) {
		this.mon6 = mon6;
	}
	public String getMon7() {
		return mon7;
	}
	public void setMon7(String mon7) {
		this.mon7 = mon7;
	}
	public String getMon8() {
		return mon8;
	}
	public void setMon8(String mon8) {
		this.mon8 = mon8;
	}
	public String getMon9() {
		return mon9;
	}
	public void setMon9(String mon9) {
		this.mon9 = mon9;
	}
	public String getMon10() {
		return mon10;
	}
	public void setMon10(String mon10) {
		this.mon10 = mon10;
	}
	public String getMon11() {
		return mon11;
	}
	public void setMon11(String mon11) {
		this.mon11 = mon11;
	}
	public String getMon12() {
		return mon12;
	}
	public void setMon12(String mon12) {
		this.mon12 = mon12;
	}
	public String getSearchYearMnSt() {
		return searchYearMnSt;
	}
	public void setSearchYearMnSt(String searchYearMnSt) {
		this.searchYearMnSt = searchYearMnSt;
	}
	public String getSearchYearMnEt() {
		return searchYearMnEt;
	}
	public void setSearchYearMnEt(String searchYearMnEt) {
		this.searchYearMnEt = searchYearMnEt;
	}
	public String getSearchSalesHqClass() {
		return searchSalesHqClass;
	}
	public void setSearchSalesHqClass(String searchSalesHqClass) {
		this.searchSalesHqClass = searchSalesHqClass;
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
	
	
	
	
	
	
	
}
