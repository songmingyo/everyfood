package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매출처원장(일별)) vo
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
public class AnlSalesLdgrDtListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;


	/*검색 시작일*/
	private String startDt;
	/**검색 종료일*/
	private String endDt;

	/**일자*/
	private String dtGubun;
	/**매출처코드*/
	private String salesCd;
	/**매출처명*/
	private String salesNm;
	/**구분*/
	private String gubun;
	/**제품명*/
	private String prdtNm;
	/**제품규격*/
	private String prdtStd;
	/**수량*/
	private String qty;
	/**단가*/
	private String price;
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
	
	
	/**수량*/
	private double prtQty;
	/**단가*/
	private double prtPrice;
	/**공급가*/
	private double prtPureAmt;
	/**부가세*/
	private double prtVatAmt;
	/**총금액*/
	private double prtTotAmt;
	/**현금*/
	private double prtPaidAmt1;
	/**어음*/
	private double prtPaidAmt2;
	/**장려금*/
	private double prtPaidAmt3;
	/**기타 */
	private double prtPaidAmt4;
	/**잔액 */
	private double prtBalAmt;
   
    //현재시간
	private String nowDate;
	//결제조건
	private String setlCon;
	
	
	
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public double getPrtQty() {
		return prtQty;
	}
	public void setPrtQty(double prtQty) {
		this.prtQty = prtQty;
	}
	public double getPrtPrice() {
		return prtPrice;
	}
	public void setPrtPrice(double prtPrice) {
		this.prtPrice = prtPrice;
	}
	public double getPrtPureAmt() {
		return prtPureAmt;
	}
	public void setPrtPureAmt(double prtPureAmt) {
		this.prtPureAmt = prtPureAmt;
	}
	public double getPrtVatAmt() {
		return prtVatAmt;
	}
	public void setPrtVatAmt(double prtVatAmt) {
		this.prtVatAmt = prtVatAmt;
	}
	public double getPrtTotAmt() {
		return prtTotAmt;
	}
	public void setPrtTotAmt(double prtTotAmt) {
		this.prtTotAmt = prtTotAmt;
	}
	public double getPrtPaidAmt1() {
		return prtPaidAmt1;
	}
	public void setPrtPaidAmt1(double prtPaidAmt1) {
		this.prtPaidAmt1 = prtPaidAmt1;
	}
	public double getPrtPaidAmt2() {
		return prtPaidAmt2;
	}
	public void setPrtPaidAmt2(double prtPaidAmt2) {
		this.prtPaidAmt2 = prtPaidAmt2;
	}
	public double getPrtPaidAmt3() {
		return prtPaidAmt3;
	}
	public void setPrtPaidAmt3(double prtPaidAmt3) {
		this.prtPaidAmt3 = prtPaidAmt3;
	}
	public double getPrtPaidAmt4() {
		return prtPaidAmt4;
	}
	public void setPrtPaidAmt4(double prtPaidAmt4) {
		this.prtPaidAmt4 = prtPaidAmt4;
	}
	public double getPrtBalAmt() {
		return prtBalAmt;
	}
	public void setPrtBalAmt(double prtBalAmt) {
		this.prtBalAmt = prtBalAmt;
	}
	public String getDtGubun() {
		return dtGubun;
	}
	public void setDtGubun(String dtGubun) {
		this.dtGubun = dtGubun;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
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
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
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
   
   
}

