package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매출처원장(일별)  vo
 * @Class : AnlSalesLdgrDtListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.05.20 j10000
 * </pre>
 * @version : 1.0
 */
public class AnlSalesLdgrMonListVo extends Page  implements Serializable {



	private static final long serialVersionUID = -6630600692804042954L;

	/**일자*/
	private String yearMon;
	/**매출처코드*/
	private String salesCd;
	/**매출처명*/
	private String salesNm;
	/**구분*/
	private String gubun;
	/**공급가*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**총금액*/
	private String totAmt;
	/**현금*/
	private String paidAmt1;
	/**어음*/
	private String paidAmt2;
	/**장려금*/
	private String paidAmt3;
	/**기타 */
	private String paidAmt4;
	/**잔액 */
	private String balAmt;
	/**정렬 */
	private String sort;   
   
	/* 조회월 시작월 */
	private String searchYearMnSt;
	/* 조회월 종료월 */
	private String searchYearMnEt;
	/* 조회 매출처 */
	private String searchSalesCd;
	
	
	
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getYearMon() {
		return yearMon;
	}
	public void setYearMon(String yearMon) {
		this.yearMon = yearMon;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getPureAmt() {
		return pureAmt;
	}
	public void setPureAmt(String pureAmt) {
		this.pureAmt = pureAmt;
	}
	public String getVatAmt() {
		return vatAmt;
	}
	public void setVatAmt(String vatAmt) {
		this.vatAmt = vatAmt;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getPaidAmt1() {
		return paidAmt1;
	}
	public void setPaidAmt1(String paidAmt1) {
		this.paidAmt1 = paidAmt1;
	}
	public String getPaidAmt2() {
		return paidAmt2;
	}
	public void setPaidAmt2(String paidAmt2) {
		this.paidAmt2 = paidAmt2;
	}
	public String getPaidAmt3() {
		return paidAmt3;
	}
	public void setPaidAmt3(String paidAmt3) {
		this.paidAmt3 = paidAmt3;
	}
	public String getPaidAmt4() {
		return paidAmt4;
	}
	public void setPaidAmt4(String paidAmt4) {
		this.paidAmt4 = paidAmt4;
	}
	public String getBalAmt() {
		return balAmt;
	}
	public void setBalAmt(String balAmt) {
		this.balAmt = balAmt;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String getSearchSalesCd() {
		return searchSalesCd;
	}
	public void setSearchSalesCd(String searchSalesCd) {
		this.searchSalesCd = searchSalesCd;
	}
	
	
	
	

}
