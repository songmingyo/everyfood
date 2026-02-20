package com.doppio.workplace.sm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매출처월별출고현황  vo
 * @Class : CusMonSlipListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.05.05 j10000
 * </pre>
 * @version : 1.0
 */
public class CusMonSlipListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**매출처코드*/
	private String salesCd;
	/**매출처명*/
	private String salesNm;
	/**과세*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**공급가*/
	private String pureTotAmt;
	/**면세*/
	private String freeAmt;
	/**합계금액*/
	private String totAmt;
	/**구분 */
	private String salesClassNm;
	/**창고명 */
	private String whNm;
	
	
	/**조회월*/
	private String yearMn;
	/**매출처구분 */
	private String salesClass;
	/**본사구분 */
	private String hqClass;
	/**창고구분 */
	private String whCd;
	
	
	
	
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
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
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
	public String getPureTotAmt() {
		return pureTotAmt;
	}
	public void setPureTotAmt(String pureTotAmt) {
		this.pureTotAmt = pureTotAmt;
	}
	public String getFreeAmt() {
		return freeAmt;
	}
	public void setFreeAmt(String freeAmt) {
		this.freeAmt = freeAmt;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getSalesClassNm() {
		return salesClassNm;
	}
	public void setSalesClassNm(String salesClassNm) {
		this.salesClassNm = salesClassNm;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}
	public String getYearMn() {
		return yearMn;
	}
	public void setYearMn(String yearMn) {
		this.yearMn = yearMn;
	}
	public String getSalesClass() {
		return salesClass;
	}
	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}
	public String getHqClass() {
		return hqClass;
	}
	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}
	

	
}
