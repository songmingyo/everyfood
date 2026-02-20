package com.doppio.workplace.sm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매입처입고조회  vo
 * @Class : BuyStoreVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.03.31 j10000
 * </pre>
 * @version : 1.0
 */
public class CusOrdListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;



	/**입고일자*/
	private String ordDt;
	/**매입처코드*/
	private String salesSlipNo;
	/**매입처명*/
	private String salesCd;
	/**품목코드*/
	private String salesNm;
	/**품목명*/
	private String prdtCd;
	/**품목명*/
	private String prdtNm;
	/**품목규격*/
	private String prdtStd;
	/**입고일자*/
	private String dlvDt;
	/**단가*/
	private String price;
	/**수량*/
	private String ordQty;
	/**공급가*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**합계금액*/
	private String totAmt;
	/**비고*/
	private String remarks1;
	/**등록자 */
	private String userNm;
	/**등록일자 */
	private String regDt;
	
	/* 조회 조건 */
	/**대분류*/
	private String lCd;
	/**중분류*/
	private String mCd;
	/**매입처 시작일*/
	private String searchStartDt;
	/**매입처 종료일*/
	private String searchEndDt;
	//본사구분
	private String searchHqClass;
	
	
	
	
	public String getSearchHqClass() {
		return searchHqClass;
	}
	public void setSearchHqClass(String searchHqClass) {
		this.searchHqClass = searchHqClass;
	}
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
	public String getSalesSlipNo() {
		return salesSlipNo;
	}
	public void setSalesSlipNo(String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
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
	public String getDlvDt() {
		return dlvDt;
	}
	public void setDlvDt(String dlvDt) {
		this.dlvDt = dlvDt;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(String ordQty) {
		this.ordQty = ordQty;
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
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getlCd() {
		return lCd;
	}
	public void setlCd(String lCd) {
		this.lCd = lCd;
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
	public String getmCd() {
		return mCd;
	}
	public void setmCd(String mCd) {
		this.mCd = mCd;
	}
	


	

}
