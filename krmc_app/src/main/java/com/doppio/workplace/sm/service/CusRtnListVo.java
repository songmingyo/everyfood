package com.doppio.workplace.sm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매출처반품현황  vo
 * @Class : CusRtnListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.03.31 j10000
 * </pre>
 * @version : 1.0
 */
public class CusRtnListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	
	/**반품일자*/
	private String rtnDt;
	/**전표*/
	private String salesSlipNo;
	/**창고명*/
	private String whNm;
	/**판매자명*/
	private String salesNm;
	/**품목코드*/
	private String prdtCd;
	/**품목명*/
	private String prdtNm;
	/**품목규격*/
	private String prdtStd;
	/**반품구분*/
	private String rtnClassNm;
	/**가격*/
	private String price;
	/**반품수량*/
	private String rtnQty;
	/**공급가액*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**합계금액*/
	private String totAmt;
	/**비고*/
	private String remarks;
	/**등록자*/
	private String regId;
	/**등록일자*/
	private String regDt;
	
	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	/**반품구분*/
	private String rtnClass;
	/**창고코드*/
	private String whCd;
	/**판매자코드*/
	private String salesCd;
	//본사구분
	private String hqClass;
	
	
	public String getHqClass() {
		return hqClass;
	}

	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}

	public String getRtnDt() {
		return rtnDt;
	}

	public void setRtnDt(String rtnDt) {
		this.rtnDt = rtnDt;
	}

	public String getSalesSlipNo() {
		return salesSlipNo;
	}

	public void setSalesSlipNo(String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}

	public String getWhNm() {
		return whNm;
	}

	public void setWhNm(String whNm) {
		this.whNm = whNm;
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

	public String getRtnClassNm() {
		return rtnClassNm;
	}

	public void setRtnClassNm(String rtnClassNm) {
		this.rtnClassNm = rtnClassNm;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRtnQty() {
		return rtnQty;
	}

	public void setRtnQty(String rtnQty) {
		this.rtnQty = rtnQty;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
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

	public String getRtnClass() {
		return rtnClass;
	}

	public void setRtnClass(String rtnClass) {
		this.rtnClass = rtnClass;
	}

	public String getWhCd() {
		return whCd;
	}

	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}

	public String getSalesCd() {
		return salesCd;
	}

	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	
	
	
	

}
