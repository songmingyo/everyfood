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
public class CusDtShipListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;


	/**고객사명*/
	private String salesNm;
	/**조회일자*/
	private String salesDt;
	/**창고명*/
	private String whNm;
	/**출고금액*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**합계금액*/
	private String totAmt;
	
	/**창고코드*/
	private String whCd;
	/**본사구분*/
	private String hqClass;
	/**매출처*/
	private String salesCd;
	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	
	
	
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
	public String getSalesDt() {
		return salesDt;
	}
	public void setSalesDt(String salesDt) {
		this.salesDt = salesDt;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
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
	public String getWhCd() {
		return whCd;
	}
	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}
	public String getHqClass() {
		return hqClass;
	}
	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
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


	
	

}
